package com.example.springboot_day01.system.mybatis;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author fly
 */
public class MybatisGenerator {

    /**
     * 代码生成    示例代码
     */
    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
                .setAuthor("fly")
                // 生成路径
                .setOutputDir("C:\\Users\\17799\\IdeaProjects\\springboot_day01\\src\\main\\java")
                // 文件覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.INPUT)
                // 设置生成的service接口的名字的首字母是否为I
                .setServiceName("%sService")
                // IEmployeeService
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig  dsConfig  = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.ORACLE)
                .setDriverName("oracle.jdbc.driver.OracleDriver")
                .setUrl("jdbc:oracle:thin:@localhost:1521:ORCL")
                .setUsername("scott")
                .setPassword("TIGER");

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        //全局大写命名
        stConfig.setCapitalMode(true)
                // 指定表名 字段名是否使用下划线
                .setDbColumnUnderline(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("T_")
                // 生成的表
                .setInclude("T_student");

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.example.springboot_day01")
                .setMapper("mapper")
                .setService("service")
                .setController("view")
                .setEntity("pojo")
                .setXml("mapper");

        //5. 整合配置
        AutoGenerator  ag = new AutoGenerator();

        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }


}
