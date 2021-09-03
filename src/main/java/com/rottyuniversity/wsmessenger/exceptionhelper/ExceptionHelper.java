package com.rottyuniversity.wsmessenger.exceptionhelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHelper {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleInputBindException(BindException ex) {
        log.error("Invalid input received: {}", ex.getMessage());

        return ResponseEntity.badRequest().body("Invalid input");
    }
}
