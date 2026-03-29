package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.post.domain.enums.ReportReason;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportReason reason;

    @Size(max = 300)
    private String detail;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    public static Report create(Post post, User reporter, ReportReason reason, String detail) {
        Report report = new Report();
        report.post = post;
        report.reporter = reporter;
        report.reason = reason;
        report.detail = detail;
        return report;
    }
}
