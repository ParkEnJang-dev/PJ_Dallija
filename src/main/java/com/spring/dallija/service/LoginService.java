package com.spring.dallija.service;

import com.spring.dallija.controller.dto.UserDto;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.exception.user.UserNotMatchPasswordException;
import com.spring.dallija.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static com.spring.dallija.common.constants.UserConst.USER_ID;
import static com.spring.dallija.common.constants.UserConst.USER_ROLE;
import static com.spring.dallija.controller.dto.UserDto.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final HttpSession session;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest){
        User user = existEmailAndPassword(loginRequest);
        session.setAttribute(USER_ID, user.getEmail());
        session.setAttribute(USER_ROLE, user.getUserRole());
    }
    @Transactional(readOnly = true)
    public User existEmailAndPassword(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .filter(u -> u.getPassword().equals(loginRequest.getPassword()))
                .orElse(null);

        if (user == null){
            throw new UserNotMatchPasswordException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    public void logout(){
        session.invalidate();
    }

    public String getLoginUser(){
        return (String) session.getAttribute(USER_ID);
    }

    public UserRole getLoginUserRole(){
        return (UserRole) session.getAttribute(USER_ROLE);
    }
}
