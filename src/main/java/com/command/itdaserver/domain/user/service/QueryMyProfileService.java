package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryMyProfileService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
}
