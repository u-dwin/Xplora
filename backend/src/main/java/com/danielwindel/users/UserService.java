package com.danielwindel.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserIdService userIdService;

    public UserService(UserRepository userRepository, UserIdService userIdService) {
        this.userRepository = userRepository;
        this.userIdService = userIdService;
    }

    public User addUser(UserDTO userRequestModel) {
        User user = new User(userRequestModel);
        user.setUserId(userIdService.generateId());

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }
}
