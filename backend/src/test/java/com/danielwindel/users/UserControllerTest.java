package com.danielwindel.users;

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

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @MockBean
    private GeoApiContext geoApiContext;

    ObjectMapper mapper = new ObjectMapper();


    UserDTO testUser = new UserDTO("test_email_address", "test_password", "traveler");

    List<String> places = List.of("testplace1", "testplace2");

    List<String> activities = List.of("testactivity1", "testactivity2");

    List<String> empty = List.of("");
    UserDetailsDTO testUserDetailsDTO = new UserDetailsDTO("link_to_picture", "description", "firstname", "lastname", places, activities);

    UserDetails testUserDetails = new UserDetails("1", "traveler", "link_to_picture", "description", "firstname", "lastname", places, activities);

    UserDetails testUserDetailsEmpty = new UserDetails("1", "traveler", "", "", "", "", empty, empty);

    @Test
    @DirtiesContext
    void addUserEndpointReturnsAddedUser() throws Exception {
        String testUserDTOJson = mapper.writeValueAsString(testUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUserDTOJson))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk()).
                andExpect(content().json(testUserDTOJson));
    }

    @Test
    @DirtiesContext
    void editUserDetailsEndpointReturnsEditedUserDetails() throws Exception {
        String testUserDetailDTOJson = mapper.writeValueAsString(testUserDetailsDTO);
        String testUserDetailsJson = mapper.writeValueAsString(testUserDetails);

        userDetailsRepository.save(testUserDetailsEmpty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/users/edit-profile/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUserDetailDTOJson))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk()).
                andExpect(content().json(testUserDetailsJson));
    }
}
