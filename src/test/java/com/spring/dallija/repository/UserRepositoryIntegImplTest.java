package com.spring.dallija.repository;

import com.spring.dallija.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryIntegImplTest {

    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    @Transactional
    public void 유저_저장() throws Exception {
        //given
        User user = new User("min", "email@com.co");

        //when
        User savedUser = userRepository.save(user);
        Optional<User> findUser = userRepository.findById(savedUser.getId());

        //then
        assertThat(findUser.get().getName()).isEqualTo(savedUser.getName());

     }


}