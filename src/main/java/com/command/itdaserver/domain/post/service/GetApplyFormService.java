package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.ApplyForm;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.ApplyFormRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.ApplyFormNotFoundException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.response.ApplyFormResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetApplyFormService {

    private final PostRepository postRepository;
    private final ApplyFormRepository applyFormRepository;

    @Transactional(readOnly = true)
    public ApplyFormResponse execute(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        ApplyForm applyForm = applyFormRepository.findByPost(post)
                .orElseThrow(() -> ApplyFormNotFoundException.EXCEPTION);

        return new ApplyFormResponse(applyForm);
    }
}
