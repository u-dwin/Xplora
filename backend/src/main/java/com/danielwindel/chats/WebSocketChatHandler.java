package com.danielwindel.chats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@AllArgsConstructor
@Service
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ChatService chatService;
}
