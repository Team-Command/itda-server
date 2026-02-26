package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.InvalidDeadlineException;
import com.command.itdaserver.domain.post.presentation.dto.request.CreatePostRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponse execute(CreatePostRequest request, CustomUserDetails customUserDetails) {
        if (request.applyDeadline().isBefore(LocalDateTime.now())) {
            throw new InvalidDeadlineException();
        }
        Post post = Post.builder()
                .title(request.title())
                .description(request.description())
                .applyDeadline(request.applyDeadline())
                .writer(userRepository.findByUserId(customUserDetails.getUserId()).orElseThrow(UserNotFoundException::new))
                .majors(request.majors())
                .build();

        return new PostResponse(postRepository.save(post), false, false);
    }
}
