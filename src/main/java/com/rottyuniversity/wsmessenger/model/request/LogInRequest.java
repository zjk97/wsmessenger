package com.rottyuniversity.wsmessenger.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LogInRequest {
    @NotNull
    private String id;

    @NotNull
    private String password;
}
