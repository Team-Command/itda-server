package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.global.entity.BaseIdEntity;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseIdEntity {

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

    @ElementCollection
    @CollectionTable(name = "post_majors", joinColumns = @JoinColumn(name = "post_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "major")
    private List<Major> majors = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likedByUsers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_bookmarks",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> bookmarkedByUsers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_members",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private List<Hashtag> hashtags = new ArrayList<>();

    @Builder
    public Post(String title, String description, LocalDateTime applyDeadline, User writer, List<Major> majors, List<User> members, List<Hashtag> hashtags) {
        this.title = title;
        this.description = description;
        this.applyDeadline = applyDeadline;
        this.writer = writer;
        this.majors = majors != null ? majors : new ArrayList<>();
        this.members = members != null ? members : new ArrayList<>();
        this.hashtags = hashtags != null ? hashtags : new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.assignPost(this);
    }

    public void toggleLike(User user) {
        if (!likedByUsers.remove(user)) likedByUsers.add(user);
    }

    public boolean isLikedBy(User user) {
        return likedByUsers.contains(user);
    }

    public long getLikeCount() {
        return likedByUsers.size();
    }

    public void toggleBookmark(User user) {
        if (!bookmarkedByUsers.remove(user)) bookmarkedByUsers.add(user);
    }

    public boolean isBookmarkedBy(User user) {
        return bookmarkedByUsers.contains(user);
    }

}
