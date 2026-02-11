package com.command.itdaserver.domain.auth.presentation.dto.request;

import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;
import com.command.itdaserver.global.validation.ValidCustomMajor;
import jakarta.validation.constraints.*;

@ValidCustomMajor
public record SignUpRequest(
        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "유저아이디는 필수입니다.")
        @Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 이하여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9_.]+$",
                message = "아이디는 영문, 숫자, 언더바(_), 마침표(.)만 사용 가능하며, 영문은 최소 1자 이상 포함되어야 합니다."
        )
        String userId,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 30, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]+$",
                message = "비밀번호는 영문, 숫자, 특수문자(@$!%*?&#)를 각각 최소 1자 이상 포함해야 합니다."
        )
        String password,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotNull(message = "전공은 필수입니다.")
        Major major,

        String customMajor, //major가 ETC일 때만 필수

        @NotNull(message = "학교는 필수입니다.")
        School school,

        @NotNull(message = "학년은 필수입니다.")
        Grade grade
) {
}
