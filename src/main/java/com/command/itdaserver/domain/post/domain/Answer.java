package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerer_id", nullable = false)
    private User answerer;

    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private QuestionOption selectedOption;

    @Lob
    private String textAnswer;

    @Builder
    public Answer(User answerer, Question question, QuestionOption selectedOption, String textAnswer) {
        this.answerer = answerer;
        this.question = question;
        this.selectedOption = selectedOption;
        this.textAnswer = textAnswer;
    }

}
