package com.command.itdaserver.domain.post.presentation.dto.request;

import com.command.itdaserver.domain.post.domain.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusRequest(
        @NotNull ApplicationStatus status
) {}
