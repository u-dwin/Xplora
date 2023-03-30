package com.danielwindel.places;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlaceServiceTest {

    private PlacesApiAutocompleteService placesApiAutocompleteService = mock(PlacesApiAutocompleteService.class);

    private GeoApiContext geoApiContext = mock(GeoApiContext.class);


    private PlaceAutocompleteRequest placeAutocompleteRequest = mock(PlaceAutocompleteRequest.class);

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

    @Test
    void isPlaceSearchReturningCorrectSearchResults() throws InterruptedException, ApiException, IOException {
        // Mock the PlacesApi response
        doReturn(placeAutocompleteRequest).when(placesApiAutocompleteService).placeAutocomplete(
                any(GeoApiContext.class),
                any(String.class),
                any(PlaceAutocompleteRequest.SessionToken.class));
        when(placeAutocompleteRequest.await()).thenReturn(predictions);

        // Call the PlaceService method with the mocked response
        List<String> actual = placeService.search(placeDTO);

        // Assert that the result matches the expected values
        List<String> expected = Arrays.asList("Berlin, Germany", "Berkeley, CA, USA");
        assertEquals(expected, actual);
    }
}