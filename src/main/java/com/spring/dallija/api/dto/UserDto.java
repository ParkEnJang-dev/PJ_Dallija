package com.spring.dallija.api.dto;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.user.GenderStatus;
import com.spring.dallija.domain.user.Health;
import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateUserRequest {
        @NotEmpty
        private String name;
        @NotEmpty
        private String email;
        @NotBlank
        private String password;
        @NotEmpty
        private String street;
        @NotEmpty
        private String zipcode;
        @NotNull
        private Integer height;
        @NotNull
        private Integer weight;
        @NotEmpty
        private String gender;

        public User toEntity() {
            Address address = new Address(street, zipcode);
            Health health = new Health(11, 12, genderStatus(gender));
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
}
