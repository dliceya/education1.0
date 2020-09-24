package com.bishe.WebSocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

public class TextMessageHandler extends TextWebSocketHandler {

    //用于存放所有建立连接的对象
    private Map<String,WebSocketSession> allClients = new HashMap<>();


    /**
     * 处理文本消息
     * session   当前发送消息的用户的连接
     * message   发送的消息是什么
     * */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject jsonObject = JSON.parseObject(new String(message.asBytes()));
        String to = jsonObject.getString("toUser");
        String toMessage = jsonObject.getString("toMessage");
        String  fromUser = (String) session.getAttributes().get("name");
        String content = fromUser+": "+toMessage;
        TextMessage toTextMessage = new TextMessage(content);//创建消息对象
        sendMessage(to,toTextMessage);
    }

    //发送消息的封装方法
    public void sendMessage(String toUser,TextMessage message) throws IOException {
        //获取到对方的连接
        WebSocketSession session = allClients.get(toUser);

        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);//发送消息
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 当链接建立的时候调用
     * */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
        //直接关闭多余连接，防止服务器异常；
            if (allClients.size() > 8){
                session.close();
            }
        
        String name = (String) session.getAttributes().get("name");//获取到拦截器中设置的name
        if (name != null) {
            allClients.put(name,session);//保存当前连接信息
            
            // 定时任务，新连接建立后10分钟关闭该连接。
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        session.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 10 * 60 * 1000);
        }
    }

    /**
     * 当连接关闭的时候
     * */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}

