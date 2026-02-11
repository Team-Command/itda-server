package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.exception.DuplicateEmailException;
import com.command.itdaserver.domain.auth.exception.DuplicateUserIdException;
import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.enums.AuthProvider;
import com.command.itdaserver.domain.user.domain.enums.Role;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(SignUpRequest request){
        log.info("회원가입 시도 - userId: {}", request.userId());

        validateDuplicateUser(request);

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = User.createLocalUser(request, encodedPassword);

        userRepository.save(user);

        log.info("회원가입 완료 - userId: {}", user.getUserId());
    }


    private void validateDuplicateUser(SignUpRequest request) {
        if (userRepository.existsByUserId(request.userId())) {
            log.warn("중복된 userId로 회원가입 시도 - userId: {}", request.userId());
            throw new DuplicateUserIdException();
        }

        if (userRepository.existsByEmail(request.email())) {
            log.warn("중복된 email로 회원가입 시도 - email: {}", request.email());
            throw new DuplicateEmailException();
        }
    }
}
