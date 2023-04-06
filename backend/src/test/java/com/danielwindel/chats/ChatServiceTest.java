package com.danielwindel.chats;

import com.danielwindel.util.ids.IdService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChatServiceTest {

    IdService idService = mock(IdService.class);
    ChatRepository chatRepository = mock(ChatRepository.class);

    ChatService chatService = new ChatService(chatRepository, idService);

    String testUserId1 = "1";

    String testUserId2 = "2";

    String[] participants = new String[]{testUserId1, testUserId2};

    Message[] messages = new Message[]{};

    Chat testChat = new Chat("3", messages, participants);

    ChatDTO testChatDTO = new ChatDTO(participants);

    @Test
    void IsAddChatReturningAddedChat() {


        when(idService.generateId()).thenReturn("3");

        when(chatRepository.save(testChat)).thenReturn(testChat);

        Chat actual = chatService.add(testChatDTO);

        assertEquals(testChat, actual);
    }
}