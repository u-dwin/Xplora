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
public class ProfileDetails {
    @Id
    private String userID;
    private String picture;
    private String description;
    private String firstName;
    private String lastName;
    private ArrayList<String> places;
    private ArrayList<String> activities;
}
