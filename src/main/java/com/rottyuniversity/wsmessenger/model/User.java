package com.rottyuniversity.wsmessenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class User {
    @Id
    private String id;

    private String nickName;

    private String password;

    private List<String> chatRooms;
}
