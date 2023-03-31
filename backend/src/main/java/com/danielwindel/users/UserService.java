package com.danielwindel.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        UserDetails userDetails = new UserDetails();

        user.setUserId(userIdService.generateId());

        userDetails.setUserID(user.userId);

        userDetails.setType(userDTO.userType);

        try {
            userDetailsRepository.save(userDetails);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public UserDetails editUserDetails(UserDetailsDTO userDetailsDTO, String id) {

        UserDetails userDetails = new UserDetails(userDetailsDTO);
        Optional<UserDetails> oldUserDetails = userDetailsRepository.findById(id);
        userDetails.setUserID(id);

        try {
            if (oldUserDetails.isEmpty()) {
                throw new NoSuchElementException();
            }
            String type = oldUserDetails.get().getType();
            userDetails.setType(type);
            return userDetailsRepository.save(userDetails);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public UserDetails getUserDetails(String id) {
        Optional<UserDetails> singleUserDetailsOptional = userDetailsRepository.findById(id);

        if (singleUserDetailsOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        UserDetails singleUserDetails;
        singleUserDetails = singleUserDetailsOptional.get();
        return singleUserDetails;
    }
}

