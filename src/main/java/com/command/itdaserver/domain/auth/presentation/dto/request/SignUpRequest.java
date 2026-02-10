package com.command.itdaserver.domain.auth.presentation.dto.request;

import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "유저아이디는 필수입니다.")
        //유저아이디 규칙 상의 예정
        String userId,

        @NotBlank(message = "비밀번호는 필수입니다.")
        //비밀번호 규칙 상의 예정
        String password,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotNull(message = "전공은 필수입니다.")
        Major major,

        String customMajor, //major가 ETC일 때만 필수(서비스에서 검증)

        @NotNull(message = "학교는 필수입니다.")
        School school,

        @NotNull(message = "학년은 필수입니다.")
        Grade grade
) {
}
