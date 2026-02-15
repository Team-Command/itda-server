package com.command.itdaserver.domain.post.domain.repository;

import com.command.itdaserver.domain.post.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
