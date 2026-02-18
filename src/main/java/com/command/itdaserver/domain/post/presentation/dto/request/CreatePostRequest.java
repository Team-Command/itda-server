package com.command.itdaserver.domain.post.presentation.dto.request;

import com.command.itdaserver.domain.post.exceptions.InvalidDeadlineException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreatePostRequest(
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String description,

        @NotNull(message = "신청 마감일은 필수입니다.")
        LocalDateTime applyDeadline
) {}
