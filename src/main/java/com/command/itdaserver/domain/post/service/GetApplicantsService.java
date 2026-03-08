package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.ApplicationRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedPostAccessException;
import com.command.itdaserver.domain.post.presentation.dto.response.ApplicantSummaryResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetApplicantsService {

    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public List<ApplicantSummaryResponse> execute(Long postId, CustomUserDetails userDetails) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        if (!post.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedPostAccessException.EXCEPTION;
        }

        return applicationRepository.findAllByPost(post).stream()
                .map(ApplicantSummaryResponse::from)
                .toList();
    }
}
