package com.danielwindel.places;


import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceAutocompleteType;
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
public class PlaceService {

    private static final Logger LOGGER = Logger.getLogger(PlaceService.class.getName());

    public GeoApiContext geoApiContext;

    public PlacesApiAutocompleteService placesApiAutocompleteService;

    public PlaceService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public List<String> search(PlaceDTO placeDTO) throws ResponseStatusException {

        Place place = new Place(placeDTO);

        String query = place.name;

        List<String> results = new ArrayList<>();

        try {
            AutocompletePrediction[] response = placesApiAutocompleteService.placeAutocomplete(geoApiContext, query, new PlaceAutocompleteRequest.SessionToken(UUID.randomUUID())).types(PlaceAutocompleteType.CITIES)
                    .await();

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