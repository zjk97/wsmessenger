package com.rottyuniversity.wsmessenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@Builder
public class ChatMessage {
    @Id
    private String id;

    private String chatRoomId;

    private String senderId;

    private String content;

    private Instant timestamp;

    private MessageStatus status;
}
