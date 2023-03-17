package com.danielwindel.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserRepository userRepository = mock(UserRepository.class);
    UserService userService = new UserService(userRepository);

    User testUser = new User("1", "test_user_email");


    @Test
    void isAddUserAddingUser() {
        when(userRepository.save(testUser)).
                thenReturn(testUser);

        User actual = userService.addUser(testUser);

        Assertions.assertEquals(actual, testUser);
    }
}
