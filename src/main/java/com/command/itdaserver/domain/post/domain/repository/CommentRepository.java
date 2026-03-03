package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Comment;
import com.command.itdaserver.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndPost(Long id, Post post);
}
