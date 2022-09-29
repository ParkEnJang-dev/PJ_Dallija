package com.spring.dallija.service;

import com.spring.dallija.domain.user.User;
import com.spring.dallija.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryIntegImplTest {

    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void 유저_저장() throws Exception {
        //given
        User user = new User("min", "email@com.co");

        //when
        User savedUser = userService.join(user);
        Optional<User> findUser = userService.findById(savedUser.getId());

        //then
        assertThat(findUser.get().getName()).isEqualTo(savedUser.getName());

    }

    @Test
    @Transactional
    public void 중복_회원_검증() throws Exception {
        //given
        User user1 = new User("min", "min@naver.com");
        User user2 = new User("min", "min@naver.com");
        userService.join(user1);

        //when

        //then
        assertThrows(IllegalStateException.class,
                () -> userService.join(user2));
    }

    @Test
    @Transactional
    public void 유저이름_변경성공() throws Exception {
        //given
        User user1 = new User("min", "min@naver.com");
        userService.join(user1);

        //when
        userService.updateName("min@naver.com","min2");
        User findUser = userService.findById(user1.getId()).get();

        //then
        assertThat(findUser.getName()).isEqualTo("min2");
    }

    @Test
    @Transactional
    public void 유저이름_변경실패__해당_이메일없음() throws Exception {
        //given
        User user1 = new User("min", "min@naver.com");
        userService.join(user1);

        //when
        //then
        assertThrows(IllegalStateException.class,
                ()->userService.updateName("min1@naver.com","min2")
        );

    }
}