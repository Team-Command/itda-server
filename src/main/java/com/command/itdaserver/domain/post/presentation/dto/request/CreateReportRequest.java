package com.command.itdaserver.domain.post.presentation.dto.request;

import com.command.itdaserver.domain.post.domain.enums.ReportReason;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateReportRequest {

    @NotNull(message = "신고 사유는 필수입니다.")
    private ReportReason reason;

    @Size(max = 300, message = "상세설명은 최대 300자까지 입력 가능합니다.")
    private String detail;
}
