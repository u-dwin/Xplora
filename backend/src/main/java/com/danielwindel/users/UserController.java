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
    public UserDetails editUserDetails(@PathVariable String id, @RequestBody UserDetailsDTO userDetailsDTO) {
        return userService.editUserDetails(userDetailsDTO, id);
    }

    @GetMapping("/profile/{id}")
    public UserDetails getUserDetails(@PathVariable String id) {
        return userService.getUserDetails(id);
    }

    @GetMapping("/experts")
    public List<UserDetails> getAllExpertDetails() {
        return null;
    }
}
