package com.danielwindel.users;

import com.danielwindel.util.ids.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserRepository userRepository = mock(UserRepository.class);

    IdService idService = mock(IdService.class);

    UserProfileRepository userProfileRepository = mock(UserProfileRepository.class);
    UserService userService = new UserService(userRepository, userProfileRepository, idService);

    UserDTO testUserDTO = new UserDTO("test_email_address", "test_password", "traveler");

    User testUser = new User("1", "test_email_address", "test_password", "traveler");

    List<String> places = List.of("testplace1", "testplace2");

    List<String> activities = List.of("testactivity1", "testactivity2");

    List<String> empty = List.of("");
    UserProfileDTO testUserProfileDTO = new UserProfileDTO("link_to_picture", "description", "firstname", "lastname", places, activities);

    UserProfile testUserProfile = new UserProfile("1", "traveler", "link_to_picture", "description", "firstname", "lastname", places, activities);

    UserProfile testUserProfileOptional = new UserProfile("1", "traveler", "", "", "", "", empty, empty);

    List<UserProfile> testUserProfileList = List.of(testUserProfile, testUserProfile);


    @Test
    void isAddUserAddingUser() {

        when(userRepository.save(testUser)).
                thenReturn(testUser);

        when(idService.generateId()).
                thenReturn("1");

        User actual = userService.addUser(testUserDTO);

        assertEquals(testUser, actual);
    }

    @Test
    void isExceptionThrownWhenUserCannotBeAddedToMongoDB() {
        when(userRepository.save(any(User.class))).
                thenThrow(new ResponseStatusException(HttpStatus.CONFLICT));

        assertThrows(ResponseStatusException.class, () ->
                userService.addUser(testUserDTO)
        );
    }

    @Test
    void isEditUserDetailsSavingNewUserDetailsCorrectly() {
        when(userProfileRepository.findById("1")).thenReturn(Optional.of(testUserProfileOptional));


        when(userProfileRepository.save(testUserProfile)).
                thenReturn(testUserProfile);


        UserProfile actual = userService.editUserDetails(testUserProfileDTO, "1");

        assertEquals(testUserProfile, actual);
    }

    @Test
    void isEditUserDetailsThrowingNoSuchElementExceptionIfUserDoesNotExists() {
        when(userProfileRepository.findById("1")).
                thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () ->
                userService.editUserDetails(testUserProfileDTO, "1")
        );
    }

    @Test
    void isSingleUserDetailsReturningSingleUserDetail() {
        when(userProfileRepository.findById("1")).thenReturn(Optional.of(testUserProfile));

        UserProfile actual = userService.getUserDetails("1");

        assertEquals(testUserProfile, actual);
    }

    @Test
    void isSingleUserDetailsThrowingExceptionWhenNotFound() {
        when(userProfileRepository.findById("1")).
                thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                userService.getUserDetails("1")
        );
    }

    @Test
    void isGetAllExpertsGettingAllExperts() {
        when(userProfileRepository.findAllByType("expert"))
                .thenReturn(testUserProfileList);

        List<UserProfile> actual = userService.getAllExperts();

        assertEquals(testUserProfileList, actual);
    }
}


