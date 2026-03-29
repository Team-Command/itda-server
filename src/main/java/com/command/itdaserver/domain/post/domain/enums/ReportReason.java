package com.command.itdaserver.domain.post.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportReason {

    SPAM("광고·홍보·스팸"),
    HATE_SPEECH("욕설·비하·혐오 표현"),
    OBSCENITY("음란·불쾌한 내용"),
    IRRELEVANT("공고 목적과 맞지 않는 내용"),
    OTHER("기타");

    private final String displayName;
}
