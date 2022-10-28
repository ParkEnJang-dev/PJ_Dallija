package com.spring.dallija.service;

import com.spring.dallija.domain.user.User;
import com.spring.dallija.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void 유저_저장() throws Exception {
        //given
        User user1 = new User("min", "min@naver.com");
        given(userRepository.save(user1))
                .willReturn(user1);

        //when
        User joinUser = userService.join(user1);

        //then
        assertThat(joinUser).isEqualTo(user1);

    }

    @Test
    public void 중복_회원() throws Exception {
        //given
        User user1 = new User("min", "min@naver.com");
        given(userRepository.save(user1))
                .willReturn(user1);
        given(userRepository.findByEmail(user1.getEmail()))
                .willReturn(Optional.of(user1));

        //when

        //then
        assertThrows(IllegalStateException.class,
                ()-> userService.join(user1));

    }

    @Test
    public void 유저이름_변경성공() throws Exception {
        //given
        User user1 = new User(1L,"min", "min@naver.com");
        User resultUser = new User(1L,"min2", "min@naver.com");
        given(userRepository.findByEmail(user1.getEmail()))
                .willReturn(Optional.of(user1));
        given(userRepository.findById(user1.getId()))
                .willReturn(Optional.of(resultUser));

        //when
        userService.updateName("min@naver.com","min2");
        User findUser = userService.findById(user1.getId()).get();

        //then
        assertThat(findUser.getName()).isEqualTo("min2");

     }

     @Test
     public void 유저이름_변경실패__해당_이메일없음() throws Exception {
         //given
         User user1 = new User(1L,"min", "min@naver.com");
         given(userRepository.findByEmail(user1.getEmail()))
                 .willReturn(Optional.of(user1));

         //when
         //then
         assertThrows(IllegalStateException.class,
                 ()->userService.updateName("min1@naver.com","min2")
         );

      }






}