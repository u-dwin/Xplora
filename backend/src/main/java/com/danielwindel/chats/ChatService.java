package com.danielwindel.chats;

import com.danielwindel.util.ids.IdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    private final IdService idService;

    public Chat add(ChatDTO chatDTO) {
        Chat chat = new Chat(chatDTO);
        Message[] messages = new Message[]{};
        String id = idService.generateId();
        chat.setId(id);
        chat.setMessages(messages);

        return chatRepository.save(chat);

    }
}
