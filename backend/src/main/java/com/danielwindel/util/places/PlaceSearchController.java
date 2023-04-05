package com.danielwindel.util.places;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/places")

@RestController
public class PlaceSearchController {
    private final PlaceService searchService;

    public PlaceSearchController(PlaceService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public List<String> search(@RequestBody PlaceDTO placeDTO) {
        return searchService.search(placeDTO);
    }
}