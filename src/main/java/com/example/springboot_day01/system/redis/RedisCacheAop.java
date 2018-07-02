package com.example.springboot_day01.system.redis;

import com.example.springboot_day01.system.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * @author fly
 * 自动加入缓存,设置一个统一的切面,
 * 这个切面将会对所有select / query 等查询有关的方法实现自定义缓存
 */

@Aspect
@Component
public class RedisCacheAop {

    /**
     * 内置json转换器
     */

    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private RedisMapper redisMapper;


    /**
     * 设置切面,因为这里我们已经在 mapper 上
     * 做了数据源的分离,所有在service下就不在分包,
     * 切面将加载到每一个service的方法上
     */

    @Pointcut("execution(public * com.example.springboot_day01.service.impl..select*(..)) || execution(public * com.example.springboot_day01.service.impl..query*(..))")
    public void redisCut(){

    }

    /**
     * 自定义缓存策略和实现方式
     * @param proceedingJoinPoint
     * @return
     */
    @Around("redisCut()")
    public Object redisAround(ProceedingJoinPoint proceedingJoinPoint) throws IOException {


        /**
         *  通过 proceedingJoinPoint 获取所请求方法的包名与方法名
         *  packageName : com.example.springboot_day01.service.impl.StudentServiceImpl
         *  methodName : querySystemRole
         *  通过 包名 + 方法名 + 参数 的方式组成redis的key,
         *  确保 key 不会重复
         */

        String packageName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String argsName = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());

        Object object = null;

        /**
         * 请求进入之后,首先去往redis当中,
         * 根据 前边生成的key去查寻redis当中是否有
         * 对应的value,如果value为空,那么执行你要
         * 请求的查询方法,将查询之后的结果集存入到结果集
         */

        String key = packageName+methodName+argsName;
        String value = redisMapper.getValue(key);

        if (value==null){
            try {
                object =  proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
                redisMapper.setValue(key,objectMapper.writeValueAsString(object));
                return object;
            } catch (Throwable throwable) {
                throw new CustomException(105,"redis出现异常");
            }
        }
        /**
         * 如果value存在于redis当中,
         * 那我们就直接将value返回出去,
         * 不在往下执行service层
         */
        else{

            /**
             * 非常关键的代码,将对象以json方式存入redis之后,
             * 在下次获取的时候就需要将 value-json 转化为一个对象,
             * 为了保证在json->object的时候不出现错误,
             * 那么我们就需要知道这个被我们访问的查询方法的返回值类型,
             * 下边的两行代码就是用来获取该方法的返回值类型,
             * 知道被访问方法的返回值类型,就可以完成 JSON->OBJECT 的转换
             */
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Class clazz = methodSignature.getMethod().getReturnType();
            object = objectMapper.readValue(value, clazz);
            return object;

        }

    }

}
