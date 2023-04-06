package com.danielwindel.chats;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatService extends TextWebSocketHandler {
}
