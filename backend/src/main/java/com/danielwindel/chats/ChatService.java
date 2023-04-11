package com.danielwindel.chats;

import com.danielwindel.util.ids.IdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@AllArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    private final IdService idService;

    private final WebSocketChatHandler webSocketChatHandler;

    public Chat add(ChatDTO chatDTO) {
        Chat chat = new Chat(chatDTO);
        Message[] messages = new Message[]{};
        String id = idService.generateId();
        chat.setId(id);
        chat.setMessages(messages);

        WebSocketSession webSocketSession;

        new WebSocketSession()

        return chatRepository.save(chat);
    }
}
