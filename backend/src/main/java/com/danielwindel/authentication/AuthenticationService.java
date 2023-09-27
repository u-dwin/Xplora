package com.danielwindel.authentication;

import com.danielwindel.users.MongoUserDetailService;
import com.danielwindel.users.UserDTO;
import com.danielwindel.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final MongoUserDetailService mongoUserDetailService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO userDTO) {
       userService.addUser(userDTO);
       var jwtToken = jwtService.generateToken(mongoUserDetailService.loadUserByUsername(userDTO.getUserEmailAddress()));
       return AuthenticationResponse.builder()
               .token(jwtToken)
               .build();
    }

    public AuthenticationResponse authenticate(UserAuthDTO userAuthDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDTO.getEmail(),
                        userAuthDTO.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(mongoUserDetailService.loadUserByUsername(userAuthDTO.getEmail()));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
