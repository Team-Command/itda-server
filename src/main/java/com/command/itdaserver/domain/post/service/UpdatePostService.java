package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Hashtag;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.HashtagRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.InvalidDeadlineException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedPostAccessException;
import com.command.itdaserver.domain.post.presentation.dto.request.UpdatePostRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.PostResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public PostResponse execute(Long postId, UpdatePostRequest request, CustomUserDetails userDetails) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        // 작성자 권한 확인
        if (!post.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedPostAccessException.EXCEPTION;
        }

        // 마감일 유효성 검증
        if (request.applyDeadline().isBefore(LocalDateTime.now())) {
            throw new InvalidDeadlineException();
        }

        Set<User> members = request.members() == null ? Collections.emptySet() :
                request.members().stream()
                        .map(userId -> userRepository.findByUserId(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION))
                        .collect(Collectors.toSet());

        List<Hashtag> hashtags = request.hashtags() == null ? Collections.emptyList() :
                request.hashtags().stream()
                        .map(name -> hashtagRepository.findByName(name)
                                .orElseGet(() -> hashtagRepository.save(new Hashtag(name))))
                        .toList();

        User requester = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        post.update(request.title(), request.description(), request.applyDeadline(), request.majors(), members, hashtags);

        return new PostResponse(post, post.isLikedBy(requester), post.isBookmarkedBy(requester));
    }
}
