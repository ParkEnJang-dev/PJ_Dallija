package com.spring.dallija.service;


import com.spring.dallija.domain.User;
import com.spring.dallija.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).size() != 0) {
            throw new IllegalStateException("이메일이 존재 합니다.");
        }
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

}
