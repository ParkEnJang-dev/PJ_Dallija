package com.spring.dallija.api;


import com.spring.dallija.api.dto.UserDto.CreateUserRequest;
import com.spring.dallija.api.dto.UserDto.CreateUserResponse;
import com.spring.dallija.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody @Valid CreateUserRequest request) {
        Long id = userService.join(request.toEntity()).getId();
        return new CreateUserResponse(id);
    }

    @PatchMapping("/v1/usersName")
    public void updateName(@RequestBody CreateUserRequest request) {
        userService.updateName(request.getEmail(), request.getName());
    }


}
