package com.danielwindel.chats;

import com.danielwindel.util.dateTime.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WebSocketChatHandlerTest {

    private WebSocketChatHandler handler;
    private ChatService chatService;
    private WebSocketSession session;

    private DateTime dateTime;

    @BeforeEach
    public void setUp() {
        chatService = mock(ChatService.class);
        dateTime = mock(DateTime.class);
        handler = new WebSocketChatHandler(chatService, dateTime);
        session = mock(WebSocketSession.class);
    }

    @Test
    void testAfterConnectionEstablished() throws Exception {
        URI uri = new URI("ws://localhost:8080/api/ws/chat/123?userId=456");
        Map<String, Object> mockAttributes = mock(HashMap.class);
        when(session.getUri()).thenReturn(uri);
        when(session.getAttributes()).thenReturn(mockAttributes);
        handler.afterConnectionEstablished(session);
        verify(session.getAttributes()).put("id", "123");
        verify(session.getAttributes()).put("userId", "456");
    }

    @Test
    void testHandleTextMessage() throws Exception {
        String chatId = null;
        String userId = null;
        String payload = "Hello, world!";
        LocalDateTime timestamp = LocalDateTime.of(2021, 9, 1, 12, 0, 0);

        TextMessage textMessage = new TextMessage(payload);
        Message message = new Message(null, "Hello, world!", null, timestamp);
        Chat chat = new Chat();

        Map<String, Object> mockSessionAttributes = mock(HashMap.class);

        when(mockSessionAttributes.get("id")).thenReturn(chatId);
        when(mockSessionAttributes.get("userId")).thenReturn(userId);

        when(chatService.updateChat(message)).thenReturn(chat);
        when(dateTime.getCurrentTime()).thenReturn(timestamp);

        handler.handleTextMessage(session, textMessage);

        assertEquals(chatId, mockSessionAttributes.get("id"));
        assertEquals(userId, mockSessionAttributes.get("userId"));
    }
}
