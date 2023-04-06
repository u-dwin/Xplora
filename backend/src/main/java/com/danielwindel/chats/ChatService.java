package com.danielwindel.chats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;
}
