package com.danielwindel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDTO {
    private String picture;
    private String description;
    private String firstName;
    private String lastName;
    private List<String> places;
    private List<String> activities;
}
