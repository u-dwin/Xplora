package com.danielwindel.places;


import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class PlacesService {
    private final GeoApiContext context;
    private static final Logger LOGGER = Logger.getLogger(PlacesService.class.getName());


    public PlacesService(@Value("${google.api.key}") String apiKey) {
        context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public List<String> search(String query) throws ResponseStatusException {

        List<String> results = new ArrayList<>();

        try {
            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).await();

            for (PlacesSearchResult result : response.results) {
                results.add(result.name);
            }

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