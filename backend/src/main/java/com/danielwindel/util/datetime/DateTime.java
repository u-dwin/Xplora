package com.danielwindel.util.datetime;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTime {
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
