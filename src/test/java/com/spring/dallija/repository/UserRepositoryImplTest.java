package com.spring.dallija.repository;

import com.spring.dallija.domain.User;
import com.spring.dallija.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    UserRepositoryImpl userRepository;

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