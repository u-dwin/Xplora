package com.danielwindel.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class UserDetailsDTO {
    private String picture;
    private String description;
    private String firstName;
    private String lastName;
    private ArrayList<String> places;
    private ArrayList<String> activities;
}
