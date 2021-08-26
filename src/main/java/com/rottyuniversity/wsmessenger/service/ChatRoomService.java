package com.rottyuniversity.wsmessenger.service;

import com.rottyuniversity.wsmessenger.model.ChatRoom;
import com.rottyuniversity.wsmessenger.model.User;
import com.rottyuniversity.wsmessenger.repository.ChatRoomRepository;
import com.rottyuniversity.wsmessenger.repository.UserRepository;
import com.rottyuniversity.wsmessenger.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;

    public ChatRoom getOrCreateChatRoom(List<User> users) {
        String chatRoomId = UserUtil.getChatRoomIdByUsers(users);
        Optional<ChatRoom> ret = chatRoomRepository.findChatRoomById(chatRoomId);
        if (ret.isPresent()) {
            return ret.get();
        }

        ChatRoom chatRoom = ChatRoom.builder().id(chatRoomId).lastActive(Instant.now()).build();
        for (User user : users) {
            user.getChatRooms().add(chatRoomId);
            userRepository.save(user);
        }
        
        return chatRoomRepository.save(chatRoom);
    }
}
