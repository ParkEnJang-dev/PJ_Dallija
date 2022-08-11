package com.spring.dallija;

import com.spring.dallija.repository.JpaUserRepository;
import com.spring.dallija.repository.MemoryUserRepository;
import com.spring.dallija.repository.UserRepository;
import com.spring.dallija.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new JpaUserRepository(em);
    }
}
