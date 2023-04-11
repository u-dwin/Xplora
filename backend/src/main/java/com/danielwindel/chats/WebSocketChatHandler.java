package com.danielwindel.chats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> openSessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        URI webSocketUri = webSocketSession.getUri();
        if (webSocketUri != null) {
            try {
                String id = webSocketUri.toString().split("api/ws/chat/")[1];
                webSocketSession.getAttributes().put("id", id);
            } catch (Exception e) {
                throw new NullPointerException("The chat id is not existent");
            }
        }
        openSessions.add(webSocketSession);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String chatId = (String) session.getAttributes().get("id");

        for (WebSocketSession openSession : openSessions) {
            String openSessionId = (String) openSession.getAttributes().get("id");
            if (openSessionId.equals(chatId)) {
                openSession.sendMessage(textMessage);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        openSessions.remove(webSocketSession);
    }
}
