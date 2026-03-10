package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.ApplyForm;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplyFormResponse {

    private Long formId;
    private String title;
    private String description;
    private List<QuestionResponse> questions = new ArrayList<>();

    public ApplyFormResponse(ApplyForm applyForm) {
        this.formId = applyForm.getId();
        this.title = applyForm.getTitle();
        this.description = applyForm.getDescription();
        this.questions = applyForm.getQuestions().stream()
                .map(QuestionResponse::new)
                .toList();
    }
}
