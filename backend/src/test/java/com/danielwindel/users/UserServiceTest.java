package com.danielwindel.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserRepository userRepository = mock(UserRepository.class);

    UserIdService userIdService = mock(UserIdService.class);
    UserService userService = new UserService(userRepository, userIdService);

    UserDTO testUserDTO = new UserDTO("test_email_address", "test_password", "Traveler");
    User testUser = new User("1", "test_email_address", "test_password", "Traveler");

    @Test
    void isAddUserAddingUser() {
        when(userRepository.save(testUser)).
                thenReturn(testUser);

        when(userIdService.generateId()).
                thenReturn("1");

        User actual = userService.addUser(testUserDTO);

        assertEquals(testUser, actual);
    }
}
