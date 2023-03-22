package com.danielwindel.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    String userEmailAddress;
    String userPassword;

    String userType;
}
