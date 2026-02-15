package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogOutService {
    private final UserRepository userRepository;


}
