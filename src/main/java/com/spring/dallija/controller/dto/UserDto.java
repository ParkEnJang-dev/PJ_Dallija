package com.spring.dallija.controller.dto;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.user.GenderStatus;
import com.spring.dallija.domain.user.Health;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.validation.annotation.OnlyGender;
import lombok.*;

import javax.validation.constraints.*;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateUserRequest {
        @NotBlank(message = "이름을 입력해주세요")
        @Size(min = 2, max = 8, message = "이름은 2자 이상 8자 이하로 입력해주세요.")
        private String name;
        @NotBlank(message = "이메일 주소를 입력해주세요")
        @Email(message = "올바른 이메일 주소를 입력해주세요.")
        private String email;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        private String password;
        @NotBlank(message = "주소를 입력해주세요")
        private String street;
        @NotBlank(message = "우편 번호를 입력해주세요")
        private String zipcode;
        @NotNull(message = "키를 입력해주세요")
        @Max(value = 300,message = "1 이상 300이하" )
        @PositiveOrZero(message = "1 이상 300이하")
        private Integer height;
        @NotNull(message = "몸무게를 입력해주세요")
        @Max(value = 300,message = "1 이상 300이하" )
        @PositiveOrZero(message = "1 이상 300이하")
        private Integer weight;
        @NotBlank(message = "성별을 입력해 주세요")
        @OnlyGender
        private String gender;

        public User toEntity() {
            Address address = new Address(street, zipcode);
            Health health = new Health( genderStatus(gender),height, weight);
            return new User(name, email, password, address, health);
        }

        private GenderStatus genderStatus(String gender) {
            switch (gender) {
                case "MAN":
                    return GenderStatus.MAN;
                case "WOMAN":
                    return GenderStatus.WOMAN;
                default:
                    return null;
            }
        }

    }


    @Data
    public static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginRequest{
        private String email;
        private String password;

    }
}
