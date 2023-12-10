package com.danielwindel.users;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<com.danielwindel.users.User> mongoUser = userRepository.findById(id);

        if (mongoUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                mongoUser.get().userId,
                mongoUser.get().userPassword,
                List.of(new SimpleGrantedAuthority("ROLE_" + mongoUser.get().userType))
        );
    }
}
