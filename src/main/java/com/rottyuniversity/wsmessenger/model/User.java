package com.rottyuniversity.wsmessenger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document
@Data
@Builder
public class User {
    @Id
    @NotNull
    private String id;

    private String nickName;

    @NotNull
    private String password;

    @NotNull
    private Set<String> chatRooms;

    @NotNull
    private Set<String> openedChats;
}
