package com.command.itdaserver.domain.user.domain;

import com.command.itdaserver.domain.auth.service.command.SignUpCommand;
import com.command.itdaserver.domain.user.domain.enums.*;
import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "u_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseIdEntity {

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId; //유저아이디(로그인용)

    private String password; //Oauth로그인시 null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true) @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Major major;

    @Column(name = "custom_major")
    private String customMajor; //major가 ETC일때만 값 존재

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private School school;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    private User(String userId, String password, AuthProvider provider,
                 String name, String email, Major major, String customMajor,
                 School school, Grade grade, Role role) {
        this.userId = userId;
        this.password = password;
        this.provider = provider;
        this.name = name;
        this.email = email;
        this.major = major;
        this.customMajor = customMajor;
        this.school = school;
        this.grade = grade;
        this.role = role;
    }

    public static User createLocalUser(SignUpCommand command, String encodedPassword) {
        return User.builder()
                .userId(command.userId())
                .password(encodedPassword)
                .provider(AuthProvider.LOCAL)
                .name(command.name())
                .email(command.email())
                .major(command.major())
                .customMajor(command.customMajor())
                .school(command.school())
                .grade(command.grade())
                .role(Role.USER)
                .build();
    }



}
