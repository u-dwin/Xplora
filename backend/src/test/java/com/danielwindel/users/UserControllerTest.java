package com.danielwindel.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();
    User testUser = new User("1", "test_email_address");

    @Test
    @DirtiesContext
    void AddUserEndpointReturnsAddedUser() throws Exception {
        String testUserJson = mapper.writeValueAsString(testUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUserJson))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk()).
                andExpect(content().json(testUserJson));
    }

}
