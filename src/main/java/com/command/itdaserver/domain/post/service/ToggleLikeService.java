package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ToggleLikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long postId, CustomUserDetails userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        User user = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        post.toggleLike(user);
    }
}
