package com.danielwindel.chats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Message {
    private String chatId;

    private String content;

    private String userId;

    private LocalDateTime sentTime;
}
