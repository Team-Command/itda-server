package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyForm extends BaseIdEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, unique = true)
    private Post post;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Lob
    @NotBlank
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "applyForm", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("questionNumber ASC")
    @BatchSize(size = 10)
    private List<Question> questions = new ArrayList<>();

    public static ApplyForm create(Post post, String title, String description) {
        ApplyForm applyForm = new ApplyForm();
        applyForm.post = post;
        applyForm.title = title;
        applyForm.description = description;
        return applyForm;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
