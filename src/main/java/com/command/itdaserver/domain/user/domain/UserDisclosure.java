package com.command.itdaserver.domain.user.domain;

import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_disclosure")
@AttributeOverride(name = "id", column = @Column(name = "ud_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserDisclosure extends BaseIdEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private boolean isNamePublic;

    @Column(nullable = false)
    private boolean isEmailPublic;

    @Column(nullable = false)
    private boolean isMajorPublic;

    @Column(nullable = false)
    private boolean isCustomMajorPublic;

    @Column(nullable = false)
    private boolean isSchoolPublic;

    @Column(nullable = false)
    private boolean isGradePublic;
}
