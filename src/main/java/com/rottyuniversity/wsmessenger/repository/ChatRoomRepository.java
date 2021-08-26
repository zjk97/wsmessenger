package com.rottyuniversity.wsmessenger.repository;

import com.rottyuniversity.wsmessenger.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findChatRoomById(String id);
}
