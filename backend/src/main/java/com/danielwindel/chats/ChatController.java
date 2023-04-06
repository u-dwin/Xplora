package com.danielwindel.chats;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @PostMapping("/add")
    Chat addChat(String id1, String id2) {
        return null;
    }
}
