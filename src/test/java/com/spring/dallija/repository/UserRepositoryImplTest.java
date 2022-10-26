package com.spring.dallija.repository;

import com.spring.dallija.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    UserRepository userRepository;

    @Test
    public void 유저_저장() throws Exception {
        //given
        User user = new User("min", "email@com.co");
        List<User> users = new ArrayList<>();
        users.add(user);
        given(userRepository.save(user)).willReturn(user);

        System.out.println(userRepository.save(user).getId());

        //when


        //then
        assertThat(userRepository.save(user)).isEqualTo(user);

    }



}