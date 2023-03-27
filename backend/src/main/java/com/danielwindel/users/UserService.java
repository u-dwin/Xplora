package com.danielwindel.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;
    private final UserIdService userIdService;

    public UserService(UserRepository userRepository, UserIdService userIdService, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userIdService = userIdService;
        this.userDetailsRepository = userDetailsRepository;
    }

    public User addUser(UserDTO userRequestModel) {
        User user = new User(userRequestModel);
        UserDetails userDetails = new UserDetails();

        user.setUserId(userIdService.generateId());

        userDetails.setUserID(user.userId);

        userDetails.setType(userRequestModel.userType);

        try {
            userDetailsRepository.save(userDetails);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public UserDetails editUserDetails(UserDetailsDTO userDetailsDTO, String id) {
        UserDetails userDetails = new UserDetails(userDetailsDTO);
        userDetails.setUserID(id);

        try {
            return userDetailsRepository.save(userDetails);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

}
