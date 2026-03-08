package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Answer;
import com.command.itdaserver.domain.post.domain.Application;
import com.command.itdaserver.domain.post.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    boolean existsByApplicationAndQuestion(Application application, Question question);
    void deleteAllByQuestionIn(List<Question> questions);
}
