package com.command.itdaserver.domain.file.presentation;

import com.command.itdaserver.domain.file.presentation.dto.response.PresignedUrlResponse;
import com.command.itdaserver.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    @GetMapping("/presigned-url")
    public PresignedUrlResponse getPresignedUrl(@RequestParam String filename) {
        return s3Service.generatePresignedUrl(filename);
    }
}
