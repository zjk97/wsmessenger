package com.rottyuniversity.wsmessenger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogInRequest {
    private String id;
    private String password;
}
