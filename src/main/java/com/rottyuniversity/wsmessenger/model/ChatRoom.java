package com.rottyuniversity.wsmessenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Builder
@Data
public class ChatRoom {
    @Id
    private String id;

    private Instant lastActive;
}
