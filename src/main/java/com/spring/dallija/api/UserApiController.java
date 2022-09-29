package com.spring.dallija.api;


import com.spring.dallija.domain.user.User;
import com.spring.dallija.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody @Valid CreateUserRequest request) {
        User user = new User(request.name, request.email);

        System.out.println(request.email);

        Long id = userService.join(user).getId();
        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateUserRequest {
        @NotEmpty
        private String name;
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
    }
}
