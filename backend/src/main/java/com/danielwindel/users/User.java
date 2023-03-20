package com.danielwindel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    String userId;
    String userEmailAddress;

    String userPassword;

    String userType;


    public User(UserDTO userRequestModel) {
        this.userEmailAddress = userRequestModel.userEmailAddress;
        this.userPassword = userRequestModel.userPassword;
        this.userType = userRequestModel.userType;
    }
}
