package com.bishe.WebSocket.blocker;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
/**
 * websocket握手的拦截器，检查握手请求和响应，对websockethandler传递属性，用于区别websocket
 * */
public class ChatIntercepter extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        
        System.out.println("握手之前");
        String url = request.getURI().toString();
        //获取连接用户名
        String name = url.substring(url.lastIndexOf("/") + 1);
        //给当前链接设置名字
        attributes.put("name",name);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception ex) {

        System.out.println("握手之后");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
