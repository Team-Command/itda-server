package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.global.entity.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @NotNull
    private Integer questionNumber;

    @NotBlank
    @Lob
    private String questionContent;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @NotNull
    private boolean multiple;

    @NotNull
    private boolean required;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options = new ArrayList<>();

    @Builder
    public Question(Integer questionNumber, String questionContent, AnswerType answerType, boolean multiple, boolean required) {
        this.questionNumber = questionNumber;
        this.questionContent = questionContent;
        this.answerType = answerType;
        this.multiple = multiple;
        this.required = required;
    }

    void assignPost(Post post) {
        this.post = post;
    }

    public void addOptions(QuestionOption option) {
        options.add(option);
        option.assignQuestion(this);
    }

}
