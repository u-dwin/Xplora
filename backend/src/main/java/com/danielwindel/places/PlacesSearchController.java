package com.danielwindel.places;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlacesSearchController {
    private final PlacesService searchService;

    public PlacesSearchController(PlacesService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("api/place/search")
    public List<String> search(@RequestParam String query) {
        return searchService.search(query);
    }
}