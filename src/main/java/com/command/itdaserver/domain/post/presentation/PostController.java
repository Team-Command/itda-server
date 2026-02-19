package com.command.itdaserver.domain.post.presentation;

import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.CreatePostRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.SubmitAnswerRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.AnswerResponse;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import com.command.itdaserver.domain.post.service.CreateApplyFormService;
import com.command.itdaserver.domain.post.service.CreatePostService;
import com.command.itdaserver.domain.post.service.GetPostService;
import com.command.itdaserver.domain.post.service.SubmitAnswerService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class PostController {

    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final CreateApplyFormService createApplyFormService;
    private final SubmitAnswerService submitAnswerService;

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return getPostService.getPost(postId);
    }

    @PostMapping("/post")
    public PostResponse createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   @Valid @RequestBody CreatePostRequest request) {
        return createPostService.execute(request, customUserDetails);
    }

    @PostMapping("/{postId}/apply-form")
    public List<Question> createApplyForm(@PathVariable Long postId, @Valid @RequestBody CreateFormRequest request) {
        return createApplyFormService.execute(postId, request);
    }

    @PostMapping("/join/{postId}")
    public List<AnswerResponse> submitAnswers(@PathVariable Long postId,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                              @Valid @RequestBody SubmitAnswerRequest request) {
        return submitAnswerService.execute(postId, request, customUserDetails);
    }

}
