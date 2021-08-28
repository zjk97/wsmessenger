package com.rottyuniversity.wsmessenger.service;

import com.rottyuniversity.wsmessenger.model.User;
import com.rottyuniversity.wsmessenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
