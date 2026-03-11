package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.ApplyForm;
import com.command.itdaserver.domain.post.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByIdAndApplyForm(Long id, ApplyForm applyForm);
}