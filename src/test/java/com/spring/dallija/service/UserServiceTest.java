package com.spring.dallija.service;

import com.spring.dallija.domain.User;
import com.spring.dallija.repository.UserRepository;
import com.spring.dallija.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void 유저_저장() throws Exception {
        //given
        User user1 = new User("min", "min@naver.com");
        List<User> users = new ArrayList<>();
        users.add(user1);


        //then
        //assertThat(findUsers.size()).isEqualTo(1);
        //assertThat(findUsers.get(0).getEmail()).isEqualTo(user1.getEmail());
    }




}