package com.spring.dallija.repository;

import com.spring.dallija.domain.user.User;

import java.util.Optional;


public interface UserRepository {

     User save(User user);
     Optional<User> findById(Long id);
     Optional<User> findByEmail(String email);

}
