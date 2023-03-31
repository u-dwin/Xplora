package com.danielwindel.users;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

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
}
