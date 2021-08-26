package com.rottyuniversity.wsmessenger.repository;

import com.rottyuniversity.wsmessenger.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findChatMessagesByChatRoomIdOrderByTimestampDesc(String chatRoomId);

    ChatMessage findChatMessageById(String id);
}
