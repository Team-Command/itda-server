package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 10)
    private List<Comment> replies = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Comment(Post post, User writer, String content, Comment parent) {
        this.post = post;
        this.writer = writer;
        this.content = content;
        this.parent = parent;
    }

    public static Comment createParent(Post post, User writer, String content) {
        return Comment.builder()
                .post(post)
                .writer(writer)
                .content(content)
                .parent(null)
                .build();
    }

    public static Comment createChild(Post post, User writer, String content, Comment parent) {
        return Comment.builder()
                .post(post)
                .writer(writer)
                .content(content)
                .parent(parent)
                .build();
    }

    public boolean isReply() {
        return parent != null;
    }
}
