package com.danielwindel.places;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

class PlaceServiceTest {

    @Mock
    private PlacesApi placesApi;

    @Mock
    private GeoApiContext geoApiContext;

    private PlaceService placeService = new PlaceService(geoApiContext);

    private PlaceDTO placeDTO = new PlaceDTO("Ber");

    private ObjectMapper mapper = new ObjectMapper();

    private List<String> results = new ArrayList<>();

    private AutocompletePrediction[] predictions = new AutocompletePrediction[]{
            new AutocompletePrediction() {{
                description = "Berlin, Germany";
            }},
            new AutocompletePrediction() {{
                description = "Berkeley, CA, USA";
            }}
    };

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        placeService = new PlaceService(geoApiContext);
    }

    @Test
    void isPlaceSearchReturningCorrectSearchResults() throws InterruptedException, ApiException, IOException {
        // Mock the PlacesApi response
        doReturn(predictions).when(placesApi).placeAutocomplete(
                any(GeoApiContext.class),
                any(String.class),
                any(PlaceAutocompleteRequest.SessionToken.class)
        ).await();

        // Call the PlaceService method with the mocked response
        List<String> actual = placeService.search(placeDTO);

        // Assert that the result matches the expected values
        List<String> expected = Arrays.asList("Berlin, Germany", "Berkeley, CA, USA");
        assertEquals(expected, actual);
    }
}