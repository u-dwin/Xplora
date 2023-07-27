package com.danielwindel.users;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            com.danielwindel.users.User mongoUser = userRepository.findByUserEmailAddress(email);

        if (mongoUser == null) {
            throw new UsernameNotFoundException("User not found");
        }


        return new User(
                mongoUser.userEmailAddress,
                mongoUser.userPassword,
                List.of(new SimpleGrantedAuthority("ROLE_" + mongoUser.userType))
        );
    }
}