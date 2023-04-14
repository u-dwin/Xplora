package com.danielwindel.chats;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor

public class ChatController {
    private final ChatService chatService;

    @PostMapping("/add")
    Chat addChat(@RequestBody ChatDTO chatDTO) {
        return chatService.add(chatDTO);
    }

    @PostMapping("/get-by-participant-id")
    List<Chat> getChatsByParticipantId(@RequestBody String id) {
        return chatService.getChatsWithParticipantId(id);
    }
}
