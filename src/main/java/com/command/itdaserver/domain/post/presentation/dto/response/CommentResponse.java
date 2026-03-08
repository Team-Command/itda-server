package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentResponse {

    private Long commentId;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentResponse> replies;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.writer = comment.getWriter().getUserId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.replies = comment.getReplies().stream()
                .map(CommentResponse::new)
                .toList();
    }
}
