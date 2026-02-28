package com.command.itdaserver.domain.post.presentation;

import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.CreatePostRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.SubmitAnswerRequest;
import com.command.itdaserver.domain.post.presentation.dto.request.UpdatePostRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.AnswerResponse;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import com.command.itdaserver.domain.post.presentation.dto.response.PostSummaryResponse;
import com.command.itdaserver.domain.post.presentation.dto.response.QuestionResponse;
import com.command.itdaserver.domain.post.service.CreateApplyFormService;
import com.command.itdaserver.domain.post.service.CreatePostService;
import com.command.itdaserver.domain.post.service.GetPostService;
import com.command.itdaserver.domain.post.service.SubmitAnswerService;
import com.command.itdaserver.domain.post.service.GetPostsService;
import com.command.itdaserver.domain.post.service.ToggleBookmarkService;
import com.command.itdaserver.domain.post.service.ToggleLikeService;
import com.command.itdaserver.domain.post.service.DeletePostService;
import com.command.itdaserver.domain.post.service.UpdatePostService;
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
    private final GetPostsService getPostsService;
    private final CreatePostService createPostService;
    private final CreateApplyFormService createApplyFormService;
    private final SubmitAnswerService submitAnswerService;
    private final ToggleLikeService toggleLikeService;
    private final ToggleBookmarkService toggleBookmarkService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    @GetMapping
    public List<PostSummaryResponse> getPosts(@RequestParam(defaultValue = "0") int page,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        return getPostsService.execute(page, userDetails);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId,
                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        return getPostService.getPost(postId, userDetails);
    }

    @PostMapping("/post")
    public PostResponse createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   @Valid @RequestBody CreatePostRequest request) {
        return createPostService.execute(request, customUserDetails);
    }

    @PostMapping("/{postId}/apply-form")
    public List<QuestionResponse> createApplyForm(@PathVariable Long postId,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails,
                                                  @Valid @RequestBody CreateFormRequest request) {
        return createApplyFormService.execute(postId, request, userDetails);
    }

    @PostMapping("/join/{postId}")
    public List<AnswerResponse> submitAnswers(@PathVariable Long postId,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                              @Valid @RequestBody SubmitAnswerRequest request) {
        return submitAnswerService.execute(postId, request, customUserDetails);
    }

    @PatchMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId,
                                   @AuthenticationPrincipal CustomUserDetails userDetails,
                                   @Valid @RequestBody UpdatePostRequest request) {
        return updatePostService.execute(postId, request, userDetails);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        deletePostService.execute(postId, userDetails);
    }

    @PostMapping("/{postId}/like")
    public void toggleLike(@PathVariable Long postId,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        toggleLikeService.execute(postId, userDetails);
    }

    @PostMapping("/{postId}/bookmark")
    public void toggleBookmark(@PathVariable Long postId,
                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        toggleBookmarkService.execute(postId, userDetails);
    }

}
