package com.danielwindel.authentication;

import com.danielwindel.users.MongoUserDetailService;
import com.danielwindel.users.UserDTO;
import com.danielwindel.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MongoUserDetailService mongoUserDetailService;

    public AuthenticationResponse register(UserDTO userDTO) {
       userService.addUser(userDTO);
        UserDetails user = mongoUserDetailService.loadUserByUsername(userDTO.getUserEmailAddress());

        var jwtToken = jwtService.generateToken(user);
       return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
    }

    public AuthenticationResponse authenticate(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUserEmailAddress(),
                        userLoginDTO.getUserPassword()
                )
        );

        UserDetails user = mongoUserDetailService.loadUserByUsername(userLoginDTO.getUserEmailAddress()
        );

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
