package com.rottyuniversity.wsmessenger.controller;

import com.rottyuniversity.wsmessenger.model.User;
import com.rottyuniversity.wsmessenger.model.request.AddNewChatRequest;
import com.rottyuniversity.wsmessenger.model.request.LogInRequest;
import com.rottyuniversity.wsmessenger.service.UserService;
import com.rottyuniversity.wsmessenger.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
        if (userService.getUserById(user.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("user already exists");
        }

        userService.saveUser(user);
        return ResponseEntity.ok("user created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody LogInRequest request) {
        log.info("log in request received");

        Optional<User> userOpt = userService.getUserById(request.getId());
        if (!userOpt.isPresent() || !userOpt.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        // TODO: add jwt tokens
        return ResponseEntity.ok("here is your token");
    }

    @PostMapping("/openedChats")
    @ResponseBody
    public Set<String> getOpenedChats(@Valid @RequestParam String userId) {
        // TODO: need to return chatroom id here, NOT single user ids
        return userService.getOpenedChats(userId);
    }

    @PostMapping("/addNewChat")
    public ResponseEntity<?> addNewChat(@Valid @RequestBody AddNewChatRequest request) {
        List<String> userIds = request.getUsers();
        for (String userId : userIds) {
            if (!userService.getUserById(userId).isPresent()) {
                return ResponseEntity.badRequest().body(String.format("\"%s\" is not a valid username", userId));
            }
        }

        String chatId = UserUtil.getChatRoomIdByUserIds(userIds);

        String senderId = request.getUserId();
        Optional<User> userOpt = userService.getUserById(senderId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.getChatRooms().add(chatId);
            user.getOpenedChats().add(chatId);

            userService.saveUser(user);

            return ResponseEntity.ok(chatId);
        }

        return ResponseEntity.badRequest().body(String.format("Sender username \"%s\" does not exist, " +
                "please report this to admin", senderId));
    }
}
