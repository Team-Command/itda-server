package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        return new PostResponse(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }
}
