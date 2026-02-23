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
    private boolean name;

    @Column(nullable = false)
    private boolean email;

    @Column(nullable = false)
    private boolean major;

    @Column(nullable = false)
    private boolean customMajor;

    @Column(nullable = false)
    private boolean school;

    @Column(nullable = false)
    private boolean grade;
}
