package com.command.itdaserver.domain.post.presentation.dto.request;

import com.command.itdaserver.domain.user.domain.enums.Major;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreatePostRequest(
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String description,

        @NotNull(message = "신청 마감일은 필수입니다.")
        LocalDateTime applyDeadline,

        @NotEmpty(message = "모집 전공은 최소 1개 이상이어야 합니다.")
        List<Major> majors,

        List<String> members
) {}
