package com.rottyuniversity.wsmessenger.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;

    private String chatRoomId;

    private String senderId;

    private String content;

    private Date timestamp;

    private MessageStatus status;
}
