package com.danielwindel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("profiles")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserProfile {
    @Id
    private String userId;

    private String type;
    private String picture;
    private String description;
    private String firstName;
    private String lastName;
    private List<String> places;
    private List<String> activities;

    public UserProfile(UserProfileDTO userProfileDTO) {
        this.picture = userProfileDTO.getPicture();
        this.description = userProfileDTO.getDescription();
        this.firstName = userProfileDTO.getFirstName();
        this.lastName = userProfileDTO.getLastName();
        this.places = userProfileDTO.getPlaces();
        this.activities = userProfileDTO.getActivities();
    }
}
