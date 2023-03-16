package com.danielwindel.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DirtiesContext
    void AddUserEndpointReturnsStatusOk() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/users/add"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }

}
