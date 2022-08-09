package com.spring.dallija.service;


import com.spring.dallija.domain.User;
import com.spring.dallija.repository.MemoryUserRepository;
import com.spring.dallija.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {

        //중복회원 검증
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();

    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
