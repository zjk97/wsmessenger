package com.rottyuniversity.wsmessenger.service;

import com.rottyuniversity.wsmessenger.model.ChatMessage;
import com.rottyuniversity.wsmessenger.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> getChatMessagesByChatRoomId(String chatRoomId) {
        return chatMessageRepository.findChatMessagesByChatRoomIdOrderByTimestampDesc(chatRoomId);
    }

    public ChatMessage getChatMessageById(String id) {
        return chatMessageRepository.findChatMessageById(id);
    }
}
