package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Lob
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "apply_deadline", nullable = false)
    private LocalDateTime applyDeadline;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Post(String title, String description, LocalDateTime applyDeadline, User writer) {
        this.title = title;
        this.description = description;
        this.applyDeadline = applyDeadline;
        this.writer = writer;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.setPost(this);
    }

}
