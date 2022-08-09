package com.spring.dallija.repository;

import com.spring.dallija.domain.User;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserRepositoryTest {

    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        User user = new User();
        user.setEmail("spring");

        //when
        repository.save(user);

        //then
        User result = repository.findById(user.getId()).get();
        assertThat(result).isEqualTo(user);

    }

    @Test
    void findById() {

    }

    @Test
    void findByEmail() {
        //given
        User user1 =new User();
        user1.setEmail("spring1");
        repository.save(user1);

        //When
        User result = repository.findByEmail(user1.getEmail()).get();

        //then
        assertThat(result).isEqualTo(user1);
    }

    @Test
    void findAll() {

        User user = new User();
        user.setName("spring");
        repository.save(user);

        User user1 = new User();
        user1.setName("spring2");
        repository.save(user1);

        List<User> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}