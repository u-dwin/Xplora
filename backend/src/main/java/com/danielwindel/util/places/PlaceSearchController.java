package com.danielwindel.util.places;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/places")
@RequiredArgsConstructor
@RestController
public class PlaceSearchController {
    private final PlaceService searchService;

    @PostMapping("/search")
    public List<String> search(@RequestBody PlaceDTO placeDTO) {
        return searchService.search(placeDTO);
    }
}