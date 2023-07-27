package com.danielwindel.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody UserDTO userRequestModel) {
        return userService.addUser(userRequestModel);
    }

    @PutMapping("/profile/{id}")
    @ResponseBody
    public UserProfile editUserDetails(@PathVariable String id, @RequestBody UserProfileDTO userProfileDTO) {
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
