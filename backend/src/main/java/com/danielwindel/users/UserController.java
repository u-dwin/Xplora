package com.danielwindel.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/profile/{id}")
    @ResponseBody
    public UserProfile editUserDetails(@PathVariable String id, @RequestBody UserProfileDTO userProfileDTO, Principal principal) {
        if (!id.equals(principal.getName())) {
            throw new IllegalArgumentException("User not authorized to edit this profile");
        }
        return userService.editUserDetails(userProfileDTO, id);
    }

    @GetMapping("/profile/{id}")
    public UserProfile getUserDetails(@PathVariable String id) {
        return userService.getUserDetails(id);
    }

    @GetMapping("/experts")
    public List<UserProfile> getAllExpertDetails() {
        return userService.getAllExperts();
    }
}
