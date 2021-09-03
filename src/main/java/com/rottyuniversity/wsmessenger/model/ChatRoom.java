package com.rottyuniversity.wsmessenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Builder
@Data
public class ChatRoom {
    @Id
    private String id;

    private Date lastActive;
}
