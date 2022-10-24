package com.spring.dallija.service;


import com.spring.dallija.domain.user.User;
import com.spring.dallija.exception.user.DuplicateEmailException;
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

    /**
     * @param user 저장할 회원의 정보.
     * @return 저장된 회원 정보.
     */

    @Transactional
    public User join(User user) {
        //중복 체크
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    @Transactional
    public void updateName(String email, String name) {
        userRepository.findByEmail(email).ifPresentOrElse(
                u -> u.changeName(name),
                () -> {
                    throw new DuplicateEmailException("이메일이 없습니다.");
                }
        );
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateEmailException("이메일이 존재합니다");
                });
    }


    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

}
