package com.danielwindel.users;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserIdService {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
