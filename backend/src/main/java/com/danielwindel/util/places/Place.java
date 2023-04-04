package com.danielwindel.util.places;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document("places")
public class Place {
    String name;

    public Place(PlaceDTO placeDTO) {
        this.name = placeDTO.name;
    }
}



