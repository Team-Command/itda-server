package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.exception.DuplicateEmailException;
import com.command.itdaserver.domain.auth.exception.DuplicateUserIdException;
import com.command.itdaserver.domain.auth.service.command.SignUpCommand;
import com.command.itdaserver.domain.user.domain.User;
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
    public void execute(SignUpCommand command){
        log.info("회원가입 시도 - userId: {}", command.userId());

        validateDuplicateUser(command);

        String encodedPassword = passwordEncoder.encode(command.password());

        User user = User.createLocalUser(command, encodedPassword);

        userRepository.save(user);

        log.info("회원가입 완료 - userId: {}", user.getUserId());
    }


    private void validateDuplicateUser(SignUpCommand command) {
        if (userRepository.existsByUserId(command.userId())) {
            log.warn("중복된 userId로 회원가입 시도 - userId: {}", command.userId());
            throw DuplicateUserIdException.EXCEPTION;
        }

        if (userRepository.existsByEmail(command.email())) {
            log.warn("중복된 email로 회원가입 시도 - email: {}", command.email());
            throw DuplicateEmailException.EXCEPTION;
        }
    }
}
