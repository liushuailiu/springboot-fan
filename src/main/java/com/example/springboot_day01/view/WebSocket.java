package com.example.springboot_day01.view;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author fly
 */
@ServerEndpoint("/web")
public class WebSocket {

    private Session session;
    public static CopyOnWriteArraySet<WebSocket> webSockets =
            new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
    }

    public void sendMessage(String text) throws IOException {
        this.session.getBasicRemote().sendText(text);
    }

}
