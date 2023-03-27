package com.danielwindel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("profiles")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDetails {
    @Id
    private String userID;

    private String type;
    private String picture;
    private String description;
    private String firstName;
    private String lastName;
    private ArrayList<String> places;
    private ArrayList<String> activities;

    public UserDetails(UserDetailsDTO userDetailsDTO) {
        this.picture = userDetailsDTO.getPicture();
        this.description = userDetailsDTO.getDescription();
        this.firstName = userDetailsDTO.getFirstName();
        this.lastName = userDetailsDTO.getLastName();
        this.places = userDetailsDTO.getPlaces();
        this.activities = userDetailsDTO.getActivities();
    }


}
