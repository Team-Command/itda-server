package com.command.itdaserver.domain.post.presentation;

import com.command.itdaserver.domain.post.presentation.dto.request.UpdateApplicationStatusRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.ApplicantSummaryResponse;
import com.command.itdaserver.domain.post.presentation.dto.response.ApplicationDetailResponse;
import com.command.itdaserver.domain.post.service.GetApplicantsService;
import com.command.itdaserver.domain.post.service.GetApplicationDetailService;
import com.command.itdaserver.domain.post.service.UpdateApplicationStatusService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class ApplicationController {

    private final GetApplicantsService getApplicantsService;
    private final GetApplicationDetailService getApplicationDetailService;
    private final UpdateApplicationStatusService updateApplicationStatusService;

    @GetMapping("/{postId}/applications")
    public List<ApplicantSummaryResponse> getApplicants(@PathVariable Long postId,
                                                        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return getApplicantsService.execute(postId, userDetails);
    }

    @GetMapping("/{postId}/applications/{applicationId}")
    public ApplicationDetailResponse getApplicationDetail(@PathVariable Long postId,
                                                          @PathVariable Long applicationId,
                                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        return getApplicationDetailService.execute(postId, applicationId, userDetails);
    }

    @PatchMapping("/{postId}/applications/{applicationId}/status")
    public ApplicationDetailResponse updateApplicationStatus(@PathVariable Long postId,
                                                             @PathVariable Long applicationId,
                                                             @Valid @RequestBody UpdateApplicationStatusRequest request,
                                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        return updateApplicationStatusService.execute(postId, applicationId, request, userDetails);
    }

}
