package com.danielwindel.places;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlacesApiAutocompleteService {

    public PlaceAutocompleteRequest placeAutocomplete(GeoApiContext context, String input, PlaceAutocompleteRequest.SessionToken sessionToken) {
        return PlacesApi.placeAutocomplete(context, input, sessionToken);
    }

    public AutocompletePrediction[] await(PlaceAutocompleteRequest placeAutocompleteRequest) throws IOException, InterruptedException, ApiException {
        return placeAutocompleteRequest.await();
    }
}
