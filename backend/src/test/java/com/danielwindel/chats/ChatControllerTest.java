package com.danielwindel.chats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ChatRepository chatRepository;

    @MockBean
    GeoApiContext geoApiContext;

    ObjectMapper mapper = new ObjectMapper();
    ChatDTO testChatDTO = new ChatDTO();

    @Test
    @DirtiesContext
    void chatCreateReturnsCreatedChat() throws Exception {
        String testChatDTOJson = mapper.writeValueAsString(testChatDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/chats/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testChatDTOJson))
                .andExpect(MockMvcResultMatchers.
                        status()
                        .isOk())
                .andExpect(content().json(testChatDTOJson));
    }
}