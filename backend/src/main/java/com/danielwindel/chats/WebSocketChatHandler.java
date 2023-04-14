package com.danielwindel.chats;

import com.danielwindel.util.datetime.DateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> openSessions = new ArrayList<>();

    private final ChatService chatService;

    private final DateTime dateTime;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        URI webSocketUri = webSocketSession.getUri();

        if (webSocketUri != null) {
            try {
                String id = webSocketUri.getPath().split("api/ws/chat/")[1];
                String userId = webSocketUri.getQuery().split("userId=")[1];
                webSocketSession.getAttributes().put("id", id);
                webSocketSession.getAttributes().put("userId", userId);
            } catch (Exception e) {
                throw new NullPointerException("The chat id is not existent");
            }
        }
        openSessions.add(webSocketSession);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String chatId = (String) session.getAttributes().get("id");
        String userId = (String) session.getAttributes().get("userId");
        LocalDateTime timeNow = dateTime.getCurrentTime();

        Message message = new Message(chatId, textMessage.getPayload(), userId, timeNow);

        chatService.updateChat(message);

        Map<String, String> messageData = new HashMap<>();

        messageData.put("userId", userId);
        messageData.put("time", timeNow.toString());
        messageData.put("text", textMessage.getPayload());

        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson = objectMapper.writeValueAsString(messageData);

        for (WebSocketSession openSession : openSessions) {
            String openSessionId = (String) openSession.getAttributes().get("id");
            if (openSessionId.equals(chatId)) {
                openSession.sendMessage(new TextMessage(messageJson));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        openSessions.remove(webSocketSession);
    }
}
