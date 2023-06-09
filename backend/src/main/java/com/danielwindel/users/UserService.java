package com.danielwindel.users;

import com.danielwindel.util.ids.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;
    private final IdService idService;

    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        UserDetails userDetails = new UserDetails();

        user.setUserId(idService.generateId());

        userDetails.setUserId(user.userId);

        userDetails.setType(userDTO.userType);

        userDetails.setPicture("");

        userDetails.setFirstName("");

        userDetails.setLastName("");

        userDetails.setDescription("");

        userDetails.setPlaces(new ArrayList<>());

        userDetails.setActivities(new ArrayList<>());

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
        userDetails.setUserId(id);

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

    public List<UserDetails> getAllExperts() {
        return userDetailsRepository.findAllByType("expert");
    }
}

