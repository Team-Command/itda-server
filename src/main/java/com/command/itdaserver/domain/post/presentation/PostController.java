package com.command.itdaserver.domain.post.presentation;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.CreatePostRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import com.command.itdaserver.domain.post.service.CreateApplyFormService;
import com.command.itdaserver.domain.post.service.CreatePostService;
import com.command.itdaserver.domain.post.service.GetPostService;
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

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return new PostResponse(getPostService.getPost(postId));
    }

    @PostMapping("/post")
    public PostResponse createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   @Valid @RequestBody CreatePostRequest request) {
        return new PostResponse(createPostService.execute(request, customUserDetails));
    }

    @PostMapping("/{postId}/apply-form")
    public List<Question> createApplyForm(@PathVariable Long postId, @Valid @RequestBody CreateFormRequest request) {
        return createApplyFormService.execute(postId, request);
    }

}
