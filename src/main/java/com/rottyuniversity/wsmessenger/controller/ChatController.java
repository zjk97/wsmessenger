package com.rottyuniversity.wsmessenger.controller;

import com.rottyuniversity.wsmessenger.model.ChatMessage;
import com.rottyuniversity.wsmessenger.service.ChatMessageService;
import com.rottyuniversity.wsmessenger.service.ChatRoomService;
import com.rottyuniversity.wsmessenger.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) {
        log.info("processing message");
        chatMessageService.saveMessage(message);

        List<String> userIds = UserUtil.getUserIdsByChatRoomId(message.getChatRoomId());
        for (String userId : userIds) {
            if (!userId.equals(message.getSenderId())) {
                messagingTemplate.convertAndSendToUser(userId, "/queue/messages", message);
            }
        }
    }
}
