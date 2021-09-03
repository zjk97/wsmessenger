package com.rottyuniversity.wsmessenger.service;

import com.google.common.collect.ImmutableSet;
import com.rottyuniversity.wsmessenger.model.User;
import com.rottyuniversity.wsmessenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

    public Set<String> getOpenedChats(String userId) {
        Optional<User> userOpt = userRepository.findUserById(userId);
        if (userOpt.isPresent()) {
            if (userOpt.get().getOpenedChats() == null) {
                return ImmutableSet.of();
            }
            return userOpt.get().getOpenedChats();
        }

        return ImmutableSet.of();
    }
}
