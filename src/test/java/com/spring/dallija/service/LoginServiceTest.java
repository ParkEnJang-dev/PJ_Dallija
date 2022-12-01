package com.spring.dallija.service;

import com.spring.dallija.domain.user.User;
import com.spring.dallija.exception.user.UserNotMatchPasswordException;
import com.spring.dallija.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static com.spring.dallija.controller.dto.UserDto.LoginRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoginServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    LoginService loginService;

    private User user;

    @BeforeEach
    void beforeEach() {
        user = new User("min", "min@naver.com", "11111111");
    }

    @Test
    public void 로그인_성공() throws Exception {
        //given
        LoginRequest loginRequest = LoginRequest
                .builder()
                .email("min@naver.com")
                .password("11111111")
                .build();

        given(userRepository.findByEmail(loginRequest.getEmail())).willReturn(Optional.of(user));

        //when
        loginService.existEmailAndPassword(loginRequest);

        //then
        then(userRepository).should(atLeastOnce()).findByEmail(loginRequest.getEmail());

    }

    @Test
    public void 로그인_실패_비밀번호_불일치() throws Exception {
        //given
        LoginRequest loginRequest = LoginRequest
                .builder()
                .email("min@naver.com")
                .password("22222222")
                .build();

        given(userRepository.findByEmail(loginRequest.getEmail())).willReturn(Optional.of(user));

        //when
        //then
        assertThrows(UserNotMatchPasswordException.class,
                () -> loginService.login(loginRequest));
    }


}