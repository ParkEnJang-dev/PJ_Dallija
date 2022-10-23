package com.spring.dallija.api;


import com.spring.dallija.api.dto.UserDto;
import com.spring.dallija.api.dto.UserDto.CreateUserRequest;
import com.spring.dallija.api.dto.UserDto.CreateUserResponse;
import com.spring.dallija.common.anotation.Login;
import com.spring.dallija.service.LoginService;
import com.spring.dallija.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Tag(name = "posts", description = "회원 저장 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody @Valid CreateUserRequest request) {
        Long id = userService.join(request.toEntity()).getId();
        return new CreateUserResponse(id);
    }

    @PatchMapping("/v1/usersName")
    public void updateName(@RequestBody CreateUserRequest request) {
        userService.updateName(request.getEmail(), request.getName());
    }

    @GetMapping("/login")
    public void login(@RequestBody UserDto.LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }

    @DeleteMapping("/logout")
    public void logout(@Login String email) {

        if (email == null) {
            System.out.println("없어");
        }
        loginService.logout();
    }

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }
        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));
        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new
                Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());
        return "세션 출력";
    }


}
