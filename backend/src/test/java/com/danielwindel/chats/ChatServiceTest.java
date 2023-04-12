package com.danielwindel.chats;

import com.danielwindel.util.ids.IdService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChatServiceTest {

    IdService idService = mock(IdService.class);
    ChatRepository chatRepository = mock(ChatRepository.class);

    ChatService chatService = new ChatService(chatRepository, idService);

    String testUserId1 = "1";

    String testUserId2 = "2";

    Message message = new Message("1", "First Message", "1");

    String[] participants = new String[]{testUserId1, testUserId2};

    ArrayList<Message> messages = new ArrayList<>();

    ArrayList<Message> messagesWithMessage = new ArrayList<>() {{
        add(message);
    }};

    Chat testChat = new Chat("3", messagesWithMessage, participants);

    Chat testChatWithMessage = new Chat("3", messages, participants);

    ChatDTO testChatDTO = new ChatDTO(participants);

    @Test
    void IsAddChatReturningAddedChat() {


        when(idService.generateId()).thenReturn("3");

        when(chatRepository.save(testChat)).thenReturn(testChat);

        Chat actual = chatService.add(testChatDTO);

        assertEquals(testChat, actual);
    }

    @Test
    void IsChatMessageAddAddingChatMessage() {
        when(chatRepository.findById("1")).thenReturn(Optional.of(testChat));

        when(chatRepository.save(testChat)).
                thenReturn(testChatWithMessage);

        Chat actual = chatService.updateChat(message);

        assertEquals(testChatWithMessage, actual);
    }
}
