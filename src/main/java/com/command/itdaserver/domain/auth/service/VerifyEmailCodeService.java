package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.domain.EmailVerification;
import com.command.itdaserver.domain.auth.domain.PasswordResetToken;
import com.command.itdaserver.domain.auth.domain.repository.EmailVerificationRepository;
import com.command.itdaserver.domain.auth.domain.repository.PasswordResetTokenRepository;
import com.command.itdaserver.domain.auth.exception.EmailNotMatchedException;
import com.command.itdaserver.domain.auth.exception.VerificationCodeExpiredException;
import com.command.itdaserver.domain.auth.exception.VerificationCodeNotMatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyEmailCodeService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void validateEmail(String userEmail, String requestEmail) {
        if (!userEmail.equals(requestEmail)) {
            throw EmailNotMatchedException.EXCEPTION;
        }
    }

    public String execute(String email, String code){
        EmailVerification verification = emailVerificationRepository.findById(email)
                .orElseThrow(() -> VerificationCodeExpiredException.EXCEPTION);

        if (!verification.isValid(code)){
            throw VerificationCodeNotMatchedException.EXCEPTION;
        }

        emailVerificationRepository.delete(verification);

        PasswordResetToken resetToken = PasswordResetToken.create(email);
        passwordResetTokenRepository.save(resetToken);

        return resetToken.getToken();
    }
}
