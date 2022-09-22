package com.spring.dallija.service;


import com.spring.dallija.domain.User;
import com.spring.dallija.repository.UserRepository;
import com.spring.dallija.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User join(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u ->{
                    throw new IllegalStateException("이메일이 존재합니다");
                });
    }

    public Optional<User> findById(Long userId){
        return userRepository.findById(userId);
    }

}
