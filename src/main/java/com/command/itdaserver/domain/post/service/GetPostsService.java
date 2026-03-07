package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.presentation.dto.response.PostSummaryResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PostSummaryResponse> execute(int page, CustomUserDetails userDetails) {
        User user = null;
        if (userDetails != null) {
            user = userRepository.findByUserId(userDetails.getUserId())
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        }

        final User finalUser = user;
        return postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, 10))
                .stream()
                .map(post -> {
                    boolean isLikedByMe = finalUser != null && post.isLikedBy(finalUser);
                    boolean isBookmarked = finalUser != null && post.isBookmarkedBy(finalUser);
                    return new PostSummaryResponse(post, isLikedByMe, isBookmarked);
                })
                .toList();
    }
}
