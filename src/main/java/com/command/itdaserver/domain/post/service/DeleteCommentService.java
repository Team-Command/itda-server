package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.repository.CommentRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.CommentNotFoundException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedCommentAccessException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void execute(Long postId, Long commentId, CustomUserDetails userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        var comment = commentRepository.findByIdAndPost(commentId, post).orElseThrow(CommentNotFoundException::new);

        if (!comment.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedCommentAccessException.EXCEPTION;
        }

        commentRepository.delete(comment);
    }
}
