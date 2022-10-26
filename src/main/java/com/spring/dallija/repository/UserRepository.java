package com.spring.dallija.repository;

import com.spring.dallija.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);

}
