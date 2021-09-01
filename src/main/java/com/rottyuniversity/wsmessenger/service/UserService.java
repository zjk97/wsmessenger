package com.rottyuniversity.wsmessenger.service;

import com.rottyuniversity.wsmessenger.model.User;
import com.rottyuniversity.wsmessenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findUserById(id);
    }

    public List<String> getOpenedChats(String userId) {
        Optional<User> userOpt = userRepository.findUserById(userId);
        if (userOpt.isPresent()) {
            if (userOpt.get().getOpenedChats() == null) {
                return new ArrayList<>();
            }
            return userOpt.get().getOpenedChats();
        }

        return new ArrayList<>();
    }
}
