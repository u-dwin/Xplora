package com.danielwindel.util.places;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class PlaceSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlaceService placeService;

    @MockBean
    GeoApiContext geoApiContext;

    ObjectMapper mapper = new ObjectMapper();
    PlaceDTO placeDTO = new PlaceDTO("Ber");

    ArrayList<String> results = new ArrayList<>();


    @Test
    void DoesSearchReturnListOfPlaces() throws Exception {

        String placeDTOJson = mapper.writeValueAsString(placeDTO);
        String resultsJson = mapper.writeValueAsString(results);

        when(placeService.search(placeDTO)).thenReturn(results);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/places/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(placeDTOJson))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(content().json(resultsJson));
    }
}

