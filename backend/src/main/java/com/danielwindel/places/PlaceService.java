package com.danielwindel.places;


import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceAutocompleteType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PlaceService {

    private static final Logger LOGGER = Logger.getLogger(PlaceService.class.getName());

    public final GeoApiContext geoApiContext;

    public final PlacesApiAutocompleteService placesApiAutocompleteService;

    public List<String> search(PlaceDTO placeDTO) throws ResponseStatusException {

        Place place = new Place(placeDTO);

        String query = place.name;

        List<String> results = new ArrayList<>();

        try {
            PlaceAutocompleteRequest request = placesApiAutocompleteService.placeAutocomplete(geoApiContext, query, new PlaceAutocompleteRequest.SessionToken(UUID.randomUUID()))
                    .types(PlaceAutocompleteType.CITIES);

            AutocompletePrediction[] response = placesApiAutocompleteService.await(request);

            results = Arrays.stream(response).map(autocompletePrediction -> autocompletePrediction.description)
                    .collect(Collectors.toCollection(ArrayList::new));

            return results;
        } catch (ApiException | IOException e) {
            LOGGER.severe("Error occurred while calling the API: " + e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warning("Thread was interrupted: " + e);
        }
        return results;
    }
}