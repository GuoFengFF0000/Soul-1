package com.qf.ws;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.client.LikeClient;
import com.qf.client.UserClient;
import com.qf.pojo.rep.Message;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.User;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import com.qf.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Component
public class ChatEndpoint {

    private static Map<String,ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    private Session session;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserClient userClient;

    @Autowired
    LikeClient likeClient;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        Cookie[] cookies = httpServletRequest.getCookies();
        CookieUtils cookieUtils = new CookieUtils();
        String token = cookieUtils.getToken(cookies);
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);
        Integer id = (Integer) verify.get("id");
        Map map = new HashMap();
        map.put("id",id);
        BaseResp byId = userClient.findById(map);
        Object data1 = byId.getData();
        User user = JSONObject.parseObject(JSONObject.toJSON(data1).toString(), User.class);

        onlineUsers.put(user.getEmail(),this);

        String message = MessageUtils.getMessage(true, null, getNames());
        broadcastAllUsers(map,message);

    }

    private void broadcastAllUsers(Map id, String message){

        List<User> friend = likeClient.findFriend(httpServletRequest,id);
        List<String> list = new ArrayList<>();
        for (User user : friend) {
            list.add(user.getEmail());
        }

        Set<String> emails = onlineUsers.keySet();
        for (String email : emails) {
            if (list.contains(email)){
                ChatEndpoint chatEndpoint = onlineUsers.get(email);
                try {
                    chatEndpoint.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Set<String> getNames(){
        return onlineUsers.keySet();
    }

    @OnMessage
    public void onMessage(String message){

        try {
            ObjectMapper mapper = new ObjectMapper();
            Message mess = mapper.readValue(message, Message.class);
            String toEmail = mess.getToEmail();
            String data = mess.getMessage();

            Cookie[] cookies = httpServletRequest.getCookies();
            CookieUtils cookieUtils = new CookieUtils();
            String token = cookieUtils.getToken(cookies);
            JWTUtils jwtUtils = new JWTUtils();
            Map verify = jwtUtils.Verify(token);
            Integer id = (Integer) verify.get("id");
            Map map = new HashMap();
            map.put("id",id);
            BaseResp byId = userClient.findById(map);
            Object data1 = byId.getData();
            User user = JSONObject.parseObject(JSONObject.toJSON(data1).toString(), User.class);

            String message1 = MessageUtils.getMessage(false, user.getEmail(), data);

            onlineUsers.get(toEmail).session.getBasicRemote().sendText(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session){

    }

}
