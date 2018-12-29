package com.board.obk_board.dto;

import com.board.obk_board.enums.SignInMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class SignInDto {
    SignInMessage msg;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class checkEmail{
        @Email
        String userEmail;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class checkPwd{
        @Length(min = 4, max = 8)
        String password;
    }
}
