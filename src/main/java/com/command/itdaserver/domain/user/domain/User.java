package com.command.itdaserver.domain.user.domain;

import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.user.domain.enums.*;
import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "u_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseIdEntity {

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId; //유저아이디(로그인용)

    private String password; //Oauth로그인시 null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    private String userImage; // 추후 s3 도입 후 리펙터링 예정

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


    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public static User of(SignUpRequest request, String password) {

        User user = User.builder()
                .userId(request.userId())
                .password(password)
                .email(request.email())
                .major(request.major())
                .customMajor(request.customMajor())
                .school(request.school())
                .grade(request.grade())
                .build();

        return user;
    }
}
