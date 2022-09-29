package com.spring.dallija.api;


import com.spring.dallija.api.dto.UserDto;
import com.spring.dallija.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public UserDto.CreateUserResponse saveUserV1(@RequestBody @Valid UserDto.CreateUserRequest request) {
        Long id = userService.join(request.toEntity()).getId();
        return new UserDto.CreateUserResponse(id);
    }


}
