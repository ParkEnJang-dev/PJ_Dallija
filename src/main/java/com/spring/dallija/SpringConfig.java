package com.spring.dallija;

import com.spring.dallija.repository.MemoryUserRepository;
import com.spring.dallija.repository.UserRepository;
import com.spring.dallija.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new MemoryUserRepository();
    }
}
