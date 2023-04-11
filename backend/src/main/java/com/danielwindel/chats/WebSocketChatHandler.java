package com.danielwindel.chats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@AllArgsConstructor
@Service
public class WebSocketChatHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New Connection Established");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        System.out.println("new message");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        System.out.println("Connection closed");
    }
}
