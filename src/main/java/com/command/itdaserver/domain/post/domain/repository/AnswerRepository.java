package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Answer;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    boolean existsByAnswererAndQuestion(User answerer, Question question);
    void deleteAllByQuestionIn(List<Question> questions);
}