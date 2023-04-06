package com.danielwindel.chats;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor

public class ChatController {
    private final ChatService chatService;

    @PostMapping("/add")
    Chat addChat(ChatDTO chatDTO) {
        return chatService.add(chatDTO);
    }
}
