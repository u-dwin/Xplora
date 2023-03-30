package com.danielwindel.places;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import org.springframework.stereotype.Service;

@Service
public class PlacesApiAutocompleteService {

    public PlaceAutocompleteRequest placeAutocomplete(GeoApiContext context, String input, PlaceAutocompleteRequest.SessionToken sessionToken) {
        return PlacesApi.placeAutocomplete(context, input, sessionToken);
    }
}
