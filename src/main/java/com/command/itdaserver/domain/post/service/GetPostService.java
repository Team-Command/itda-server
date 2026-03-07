package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id, CustomUserDetails userDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        boolean isLikedByMe = false;
        boolean isBookmarked = false;

        if (userDetails != null) {
            User user = userRepository.findByUserId(userDetails.getUserId())
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);
            isLikedByMe = post.isLikedBy(user);
            isBookmarked = post.isBookmarkedBy(user);
        }

        return new PostResponse(post, isLikedByMe, isBookmarked);
    }
}
