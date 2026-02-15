package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.presentation.dto.request.CreatePostRequest;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PostRepository postRepository;

    @Transactional
    public Post execute(CreatePostRequest request, CustomUserDetails customUserDetails) {
        Post post = Post.builder()
                .title(request.title())
                .description(request.description())
                .applyDeadline(request.applyDeadline())
                .writer(customUserDetails.getUsername())
                .build();

        return postRepository.save(post);
    }
}
