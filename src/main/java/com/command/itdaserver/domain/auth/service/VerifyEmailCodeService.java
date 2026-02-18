package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.domain.EmailVerification;
import com.command.itdaserver.domain.auth.domain.PasswordResetToken;
import com.command.itdaserver.domain.auth.domain.repository.EmailVerificationRepository;
import com.command.itdaserver.domain.auth.domain.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyEmailCodeService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public String execute(String email, String code){
        EmailVerification verification = emailVerificationRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("인증 코드가 만료되었거나 존재하지 않습니다."));

        if (!verification.isValid(code)){
            throw new RuntimeException("인증 코드가 일치하지 않습니다.");
        }

        emailVerificationRepository.delete(verification);

        PasswordResetToken resetToken = PasswordResetToken.create(email);
        passwordResetTokenRepository.save(resetToken);

        return resetToken.getToken();
    }
}
