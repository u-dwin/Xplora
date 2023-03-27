package com.danielwindel.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ProfileDetailsRepository profileDetailsRepository;
    private final UserIdService userIdService;

    public UserService(UserRepository userRepository, UserIdService userIdService, ProfileDetailsRepository profileDetailsRepository) {
        this.userRepository = userRepository;
        this.userIdService = userIdService;
        this.profileDetailsRepository = profileDetailsRepository;
    }

    public User addUser(UserDTO userRequestModel) {
        User user = new User(userRequestModel);
        ProfileDetails profileDetails = new ProfileDetails();

        user.setUserId(userIdService.generateId());

        profileDetails.setUserID(user.userId);

        profileDetailsRepository.save(profileDetails);

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

}
