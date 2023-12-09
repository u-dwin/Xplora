package com.danielwindel.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginDTO {
        String userEmailAddress;
        String userPassword;
}
