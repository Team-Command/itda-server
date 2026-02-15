package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserAccount {
    private final UserRepository userRepository;

    public void execute() {

    }
}
