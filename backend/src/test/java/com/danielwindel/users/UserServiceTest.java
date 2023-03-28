package com.danielwindel.users;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserRepository userRepository = mock(UserRepository.class);

    UserIdService userIdService = mock(UserIdService.class);

    UserDetailsRepository userDetailsRepository = mock(UserDetailsRepository.class);
    UserService userService = new UserService(userRepository, userIdService, userDetailsRepository);

    UserDTO testUserDTO = new UserDTO("test_email_address", "test_password", "traveler");

    User testUser = new User("1", "test_email_address", "test_password", "traveler");

    List<String> places = List.of("testplace1", "testplace2");

    List<String> activities = List.of("testactivity1", "testactivity2");

    List<String> empty = List.of("");
    UserDetailsDTO testUserDetailsDTO = new UserDetailsDTO("link_to_picture", "description", "firstname", "lastname", places, activities);

    UserDetails testUserDetails = new UserDetails("1", "traveler", "link_to_picture", "description", "firstname", "lastname", places, activities);

    UserDetails testUserDetailsOptional = new UserDetails("1", "traveler", "", "", "", "", empty, empty);

    @Test
    void isAddUserAddingUser() {

        when(userRepository.save(testUser)).
                thenReturn(testUser);

        when(userIdService.generateId()).
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
        when(userDetailsRepository.findById("1")).thenReturn(Optional.of(testUserDetailsOptional));


        when(userDetailsRepository.save(testUserDetails)).
                thenReturn(testUserDetails);


        UserDetails actual = userService.editUserDetails(testUserDetailsDTO, "1");

        assertEquals(testUserDetails, actual);
    }

    @Test
    void isEditUserDetailsThrowingExceptionIfUserNotExists() {

    }
}
