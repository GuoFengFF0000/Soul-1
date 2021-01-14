package com.qf.ws;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/talk/{id}")
public class TalkerEndpoint {

    //连接数
    private static int onlineCount = 0;

    private Session session;

    private static Map<String, TalkerEndpoint> onlineUsers = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") String id) {
        this.session = session;

        onlineUsers.put(id, this);
        addOnlineCount();
        sessionPool.put(id, session);//把对应用户id的session放到sessionPool中，用于单点信息发送
        System.out.println("【websocket消息】 有新连接加入！用户id" + id + "，当前在线人数为" + getOnlineCount());

    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        onlineUsers.remove(id);
        subOnlineCount();           //在线数减1
        System.out.println("【websocket消息】 连接断开！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:" + message);
    }

    public void sendOneMessage(String userId, String message) {
        try {
            // 防止推送到客户端的信息太多导致弹窗太快
            Thread.sleep(500);
            System.out.println("用户" + userId + "【websocket消息】单点消息:" + message);
            Session session = sessionPool.get(userId);
            if (session != null) {
                // getAsyncRemote是异步发送，加锁防止上一个消息还未发完下一个消息又进入了此方法
                // 也就是防止多线程中同一个session多次被调用报错,虽然上面睡了0.5秒，为了保险最好加锁
                synchronized (session) {
                    session.getAsyncRemote().sendText(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public static synchronized void addOnlineCount() {
        TalkerEndpoint.onlineCount++;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void subOnlineCount() {
        TalkerEndpoint.onlineCount--;
    }

}
