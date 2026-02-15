package com.command.itdaserver.domain.post.presentation.dto.request;

import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import lombok.Data;

import java.util.List;

@Data
public class CreateFormRequest {
    private List<QuestionDto> questions;

    @Data
    public static class QuestionDto {
        private Integer questionNumber; // 질문 번호 (순서)
        private String questionContent; // 질문 내용
        private AnswerType answerType; // 답변 유형 (객관식, 주관식 등)
        private Boolean multiple; // 객관식 질문에서 여러 답변 허용 여부
        private Boolean required; // 질문에서 필수 응답 여부
        private List<OptionDto> options; // 객관식 질문의 선택지 목록
    }

    @Data
    public static class OptionDto {
        private Integer answerNumber; // 객관식의 경우 보기 순서
        private String answerContent; // 객관식의 경우 보기 내용
    }
}
