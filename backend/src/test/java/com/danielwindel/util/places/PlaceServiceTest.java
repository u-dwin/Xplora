package com.danielwindel.util.places;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceAutocompleteType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlaceServiceTest {

    PlacesApiAutocompleteService placesApiAutocompleteService = mock(PlacesApiAutocompleteService.class);

    GeoApiContext geoApiContext = mock(GeoApiContext.class);


    PlaceAutocompleteRequest placeAutocompleteRequest = mock(PlaceAutocompleteRequest.class);

    PlaceService placeService = new PlaceService(geoApiContext, placesApiAutocompleteService);

    PlaceDTO placeDTO = new PlaceDTO("Ber");


    AutocompletePrediction[] predictions = new AutocompletePrediction[]{
            new AutocompletePrediction() {{
                description = "Berlin, Germany";
            }},
            new AutocompletePrediction() {{
                description = "Berkeley, CA, USA";
            }}
    };

    @Test
    void isPlaceSearchReturningCorrectSearchResults() throws InterruptedException, ApiException, IOException {
        doReturn(placeAutocompleteRequest).when(placesApiAutocompleteService).placeAutocomplete(
                any(GeoApiContext.class),
                any(String.class),
                any(PlaceAutocompleteRequest.SessionToken.class));

        doReturn(placeAutocompleteRequest).when(placeAutocompleteRequest).types((PlaceAutocompleteType.CITIES));


        doReturn(predictions).when(placesApiAutocompleteService).await(placeAutocompleteRequest);

        List<String> actual = placeService.search(placeDTO);

        List<String> expected = Arrays.asList("Berlin, Germany", "Berkeley, CA, USA");
        assertEquals(expected, actual);
    }

    @Test
    void doesPlaceSearchHandleApiExceptionCorrectly() throws Exception {
        doReturn(placeAutocompleteRequest).when(placesApiAutocompleteService).placeAutocomplete(
                any(GeoApiContext.class),
                any(String.class),
                any(PlaceAutocompleteRequest.SessionToken.class)
        );

        doReturn(placeAutocompleteRequest).when(placeAutocompleteRequest).types((PlaceAutocompleteType.CITIES));

        doThrow(ApiException.class).when(placesApiAutocompleteService).await(placeAutocompleteRequest);

        placeService = new PlaceService(geoApiContext, placesApiAutocompleteService);

        List<String> actual = placeService.search(placeDTO);

        assertTrue(actual.isEmpty());
    }

    @Test
    void doesPlaceSearchHandleIOExceptionCorrectly() throws Exception {
        doReturn(placeAutocompleteRequest).when(placesApiAutocompleteService).placeAutocomplete(
                any(GeoApiContext.class),
                any(String.class),
                any(PlaceAutocompleteRequest.SessionToken.class)
        );

        doReturn(placeAutocompleteRequest).when(placeAutocompleteRequest).types((PlaceAutocompleteType.CITIES));

        doThrow(IOException.class).when(placesApiAutocompleteService).await(placeAutocompleteRequest);

        List<String> results = placeService.search(placeDTO);

        assertTrue(results.isEmpty());
    }
}