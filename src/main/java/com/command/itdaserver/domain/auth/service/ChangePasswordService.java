package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.domain.PasswordResetToken;
import com.command.itdaserver.domain.auth.domain.repository.PasswordResetTokenRepository;
import com.command.itdaserver.domain.auth.exception.ResetTokenNotFoundException;
import com.command.itdaserver.domain.auth.presentation.dto.request.ChangePasswordRequest;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.domain.user.exception.UserNotMatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Transactional
    public void execute(String email, String userId, ChangePasswordRequest request){

        PasswordResetToken resetToken = passwordResetTokenRepository.findById(request.resetToken())
                .orElseThrow(() -> ResetTokenNotFoundException.EXCEPTION);

        if (!resetToken.getEmail().equals(email)) {
            throw ResetTokenNotFoundException.EXCEPTION; // 또는 UnauthorizedException
        }

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.changePassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        passwordResetTokenRepository.delete(resetToken);
    }
}
