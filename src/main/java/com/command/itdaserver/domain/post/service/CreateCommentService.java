package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Comment;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.CommentRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateCommentRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.CommentResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse execute(Long postId, CreateCommentRequest request, CustomUserDetails userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        User writer = userRepository.findByUserId(userDetails.getUserId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Comment comment = commentRepository.save(Comment.createParent(post, writer, request.content()));

        return new CommentResponse(comment);
    }
}
