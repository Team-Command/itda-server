package com.command.itdaserver.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @NotNull
    private Integer answerNumber;

    @NotBlank
    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @Builder
    public QuestionOption(Integer answerNumber, String answerContent) {
        this.answerNumber = answerNumber;
        this.answerContent = answerContent;
    }

    void assignQuestion(Question question) {
        this.question = question;
    }

}

