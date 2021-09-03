package com.rottyuniversity.wsmessenger.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class AddNewChatRequest {
    @NotNull
    private String userId;

    @NotNull
    private List<String> users;

    @NotNull
    private Date timestamp;
}
