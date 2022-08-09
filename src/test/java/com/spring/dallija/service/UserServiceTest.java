package com.spring.dallija.service;

import com.spring.dallija.domain.User;
import com.spring.dallija.repository.MemoryUserRepository;
import com.spring.dallija.repository.UserRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @Test
    void 회원가입() throws Exception {
        //Given
        User user = new User();
        user.setEmail("spring");

        //When
        userService.join(user);

        //Then
        User result = userRepository.findByEmail("spring").get();

        assertEquals(result.getEmail(), user.getEmail());
    }

    @Test
    void 중복회원예외() throws Exception{
        //Given
        User user = new User();
        user.setEmail("spring");
        userService.join(user);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(user)); //예외 발생해야됨.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}