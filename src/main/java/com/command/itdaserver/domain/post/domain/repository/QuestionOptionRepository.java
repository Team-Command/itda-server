package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    Optional<QuestionOption> findByIdAndQuestion(Long id, Question question);

    Optional<QuestionOption> findByAnswerNumberAndQuestion(Integer answerNumber, Question question);
}