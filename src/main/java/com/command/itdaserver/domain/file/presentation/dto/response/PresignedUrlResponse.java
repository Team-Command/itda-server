package com.command.itdaserver.domain.file.presentation.dto.response;

public record PresignedUrlResponse(
        String presignedUrl,
        String fileUrl
) {}
