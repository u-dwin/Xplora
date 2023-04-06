package com.danielwindel.chats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("chats")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Chat {
    @Id
    private String id;
    private Message[] messages;
    private String[] participants;

    public Chat(ChatDTO chatDTO) {
        this.participants = chatDTO.getParticipants();
    }
}
