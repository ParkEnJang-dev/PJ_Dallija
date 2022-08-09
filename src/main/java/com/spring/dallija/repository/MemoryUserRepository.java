package com.spring.dallija.repository;

import com.spring.dallija.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepository {

    private static ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(sequence,user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
