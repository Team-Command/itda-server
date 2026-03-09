package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.post.domain.enums.ApplicationStatus;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
@Table(
        name = "application",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "applicant_id"})
)
public class Application extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @CreationTimestamp
    @Column(name = "applied_at", nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 10)
    private List<Answer> answers = new ArrayList<>();

    public static Application create(Post post, User applicant) {
        Application application = new Application();
        application.post = post;
        application.applicant = applicant;
        application.status = ApplicationStatus.PENDING;
        return application;
    }

    public void updateStatus(ApplicationStatus status) {
        this.status = status;
    }

}
