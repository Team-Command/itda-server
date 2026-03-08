package com.command.itdaserver.domain.post.presentation;

import com.command.itdaserver.domain.post.presentation.dto.request.CreateCommentRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.CommentResponse;
import com.command.itdaserver.domain.post.service.CreateCommentService;
import com.command.itdaserver.domain.post.service.CreateReplyService;
import com.command.itdaserver.domain.post.service.DeleteCommentService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;
    private final CreateReplyService createReplyService;
    private final DeleteCommentService deleteCommentService;

    @PostMapping("/{postId}/comments")
    public CommentResponse createComment(@PathVariable Long postId,
                                         @AuthenticationPrincipal CustomUserDetails userDetails,
                                         @Valid @RequestBody CreateCommentRequest request) {
        return createCommentService.execute(postId, request, userDetails);
    }

    @PostMapping("/{postId}/comments/{commentId}/replies")
    public CommentResponse createReply(@PathVariable Long postId,
                                       @PathVariable Long commentId,
                                       @AuthenticationPrincipal CustomUserDetails userDetails,
                                       @Valid @RequestBody CreateCommentRequest request) {
        return createReplyService.execute(postId, commentId, request, userDetails);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        deleteCommentService.execute(postId, commentId, userDetails);
    }
}
