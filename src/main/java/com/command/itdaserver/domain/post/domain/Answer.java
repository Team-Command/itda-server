package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private QuestionOption selectedOption;

    @Lob
    private String textAnswer;

    @Builder
    public Answer(Application application, Question question, QuestionOption selectedOption, String textAnswer) {
        this.application = application;
        this.question = question;
        this.selectedOption = selectedOption;
        this.textAnswer = textAnswer;
    }

}
