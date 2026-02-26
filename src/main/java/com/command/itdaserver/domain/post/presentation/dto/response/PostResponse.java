package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.user.domain.enums.Major;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponse {

    private Long postId;
    private String writer;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime applyDeadline;
    private List<Major> majors;
    private List<String> members;
    private long likeCount;
    private boolean isLikedByMe;
    private boolean isBookmarked;
    private List<QuestionResponse> questions = new ArrayList<>();

    public PostResponse(Post post, boolean isLikedByMe, boolean isBookmarked) {
        this.postId = post.getId();
        this.writer = post.getWriter().getUserId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt();
        this.applyDeadline = post.getApplyDeadline();
        this.majors = post.getMajors();
        this.members = post.getMembers().stream().map(user -> user.getUserId()).toList();
        this.likeCount = post.getLikeCount();
        this.isLikedByMe = isLikedByMe;
        this.isBookmarked = isBookmarked;
        this.questions = post.getQuestions().stream()
                .map(QuestionResponse::new)
                .toList();
    }
}
