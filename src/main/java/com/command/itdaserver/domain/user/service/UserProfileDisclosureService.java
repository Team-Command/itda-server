package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileDisclosureService {
    private final UserRepository userRepository;

}
