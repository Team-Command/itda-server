package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.profile.exception.UserIdDuplicateException;
import com.command.itdaserver.domain.profile.presentation.dto.request.UserProfileRequest;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMyProfileService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    public void execute(UserProfileRequest request){
        userRepository.findByUserId(request.userId())
                .orElseThrow(() -> UserIdDuplicateException.EXCEPTION);

        
    }
}
