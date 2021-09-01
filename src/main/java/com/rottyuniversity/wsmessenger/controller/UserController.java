package com.rottyuniversity.wsmessenger.controller;

import com.rottyuniversity.wsmessenger.model.LogInRequest;
import com.rottyuniversity.wsmessenger.model.User;
import com.rottyuniversity.wsmessenger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/v1/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (userService.getUserById(user.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("user already exists");
        }

        userService.saveUser(user);
        return ResponseEntity.ok("user created successfully");
    }

    @PostMapping("/user/v1/login")
    public ResponseEntity<?> logIn(@RequestBody LogInRequest request) {
        log.info("log in request received");

        Optional<User> userOpt = userService.getUserById(request.getId());
        if (!userOpt.isPresent() || !userOpt.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        // TODO: add jwt tokens
        return ResponseEntity.ok("here is your token");
    }

    @PostMapping("/user/v1/openedChats")
    @ResponseBody
    public List<String> getOpenedChats(@RequestParam String userId) {
        // TODO: need to return chatroom id here, NOT single user ids
        return userService.getOpenedChats(userId);
    }
}
