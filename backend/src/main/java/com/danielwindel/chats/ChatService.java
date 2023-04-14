package com.danielwindel.chats;

import com.danielwindel.util.ids.IdService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    private final IdService idService;

    public Chat add(ChatDTO chatDTO) {
        Chat chat = new Chat(chatDTO);
        ArrayList<Message> messages = new ArrayList<>();
        String id = idService.generateId();
        chat.setId(id);
        chat.setMessages(messages);

        return chatRepository.save(chat);
    }

    public Chat updateChat(Message message) {
        Optional<Chat> oldChat = chatRepository.findById(message.getChatId());

        try {
            if (oldChat.isEmpty()) {
                throw new NoSuchElementException();
            }
            Chat newChat = oldChat.get();

            ArrayList<Message> oldMessages = newChat.getMessages();

            oldMessages.add(message);

            newChat.setMessages(oldMessages);

            return chatRepository.save(newChat);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public List<Chat> getChatsWithParticipantId(String id) {
        return chatRepository.findAllByParticipants(id);
    }
}
