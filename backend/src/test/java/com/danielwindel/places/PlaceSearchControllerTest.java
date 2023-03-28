package com.danielwindel.places;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class PlaceSearchControllerTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();
    PlaceDTO placeDTO = new PlaceDTO("TestPlace");

    @Autowired
    PlaceService placeService;

    @Test
    void DoesSearchReturnListOfPlaces() throws Exception {
        String placeDTOJson = mapper.writeValueAsString(placeDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/places/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(placeDTOJson))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }
}

