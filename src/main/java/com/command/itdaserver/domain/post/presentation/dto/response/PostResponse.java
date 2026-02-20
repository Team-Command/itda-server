package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class PostResponse {

    private Long postId;
    private String writer;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime applyDeadline;
    private List<QuestionResponse> questions = new ArrayList<>();

    public PostResponse(Post post) {
        this.postId = post.getId();
        this.writer = post.getWriter().getUserId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt();
        this.applyDeadline = post.getApplyDeadline();
        this.questions = post.getQuestions().stream()
                .map(QuestionResponse::new)
                .toList();
    }
}
