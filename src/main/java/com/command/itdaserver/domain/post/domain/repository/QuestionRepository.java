package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByIdAndPost(Long id, Post post);
}