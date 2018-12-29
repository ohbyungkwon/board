package com.board.obk_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserDto {
    private boolean isSuccess;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createUser{
        @Email
        @NotEmpty(message = "username is null")
        private String username;

        @Length(min = 4, max = 8)
        @NotEmpty(message = "password is null")
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateUser{
        @NotEmpty(message = "password is null")
        @Length(min = 4, max = 8)
        private String password;
    }
}
