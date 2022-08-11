package com.spring.dallija.service;

import com.spring.dallija.domain.User;
import com.spring.dallija.repository.MemoryUserRepository;
import com.spring.dallija.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired UserService userService;
    @Autowired  UserRepository userRepository;


    @Test
    void 회원가입() throws Exception {
        //Given
        User user = new User();
        user.setEmail("jms99@naver.com");
        user.setName("anything");
        user.setPassword("fff");

        //When
        Long saveId = userService.join(user);

        //Then
        User result = userRepository.findById(saveId).get();

        assertEquals(result.getEmail(), user.getEmail());
    }

    @Test
    void 회원찾기() throws Exception {
        User user = new User();
        user.setEmail("jms99@naver.com");
        //userService.join(user);

        User result = userService.findUserValidation(user).get();

        assertEquals(result.getEmail(), user.getEmail());
    }

    @Test
    void 중복회원예외() throws Exception {
        //Given
        User user = new User();
        user.setEmail("jms3499@naver.com");
        userService.join(user);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user)); //예외 발생해야됨.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}