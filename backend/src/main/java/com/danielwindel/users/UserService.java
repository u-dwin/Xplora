package com.danielwindel.users;

import com.danielwindel.util.ids.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;
    private final IdService idService;

    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        UserProfile userProfile = new UserProfile();

        user.setUserId(idService.generateId());

        user.setUserPassword(passwordEncoder.encode(userDTO.userPassword));

        userProfile.setUserId(user.userId);

        userProfile.setType(userDTO.userType);

        userProfile.setPicture("");

        userProfile.setFirstName("");

        userProfile.setLastName("");

        userProfile.setDescription("");

        userProfile.setPlaces(new ArrayList<>());

        userProfile.setActivities(new ArrayList<>());

        try {
            userProfileRepository.save(userProfile);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public UserProfile editUserDetails(UserProfileDTO userProfileDTO, String id) {

        UserProfile userProfile = new UserProfile(userProfileDTO);
        Optional<UserProfile> oldUserDetails = userProfileRepository.findById(id);
        userProfile.setUserId(id);

        try {
            if (oldUserDetails.isEmpty()) {
                throw new NoSuchElementException();
            }
            String type = oldUserDetails.get().getType();
            userProfile.setType(type);
            return userProfileRepository.save(userProfile);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public UserProfile getUserDetails(String id) {
        Optional<UserProfile> singleUserDetailsOptional = userProfileRepository.findById(id);

        if (singleUserDetailsOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        UserProfile singleUserProfile;
        singleUserProfile = singleUserDetailsOptional.get();
        return singleUserProfile;
    }

    public List<UserProfile> getAllExperts() {
        return userProfileRepository.findAllByType("expert");
    }
}

