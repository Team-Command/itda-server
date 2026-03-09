package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.ApplicationRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.ApplicationNotFoundException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedPostAccessException;
import com.command.itdaserver.domain.post.presentation.dto.response.ApplicationDetailResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetApplicationDetailService {

    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public ApplicationDetailResponse execute(Long postId, Long applicationId, CustomUserDetails userDetails) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        if (!post.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedPostAccessException.EXCEPTION;
        }

        return applicationRepository.findByIdAndPost(applicationId, post)
                .map(ApplicationDetailResponse::from)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
    }
}
