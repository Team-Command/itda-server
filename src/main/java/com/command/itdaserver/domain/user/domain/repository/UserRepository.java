package com.command.itdaserver.domain.user.domain.repository;

import com.command.itdaserver.domain.user.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(@NotBlank(message = "유저아이디는 필수입니다.") String userId);

    boolean existsByEmail(@NotBlank(message = "이메일은 필수입니다.") @Email(message = "올바른 이메일 형식이 아닙니다.") String email);
}
