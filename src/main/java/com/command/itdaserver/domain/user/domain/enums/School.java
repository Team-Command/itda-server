package com.command.itdaserver.domain.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum School {
    DAEGU_SOFTWARE_MEISTER("대구소프트웨어마이스터고등학교"),
    DAEDEOK_SOFTWARE_MEISTER("대덕소프트웨어마이스터고등학교"),
    BUSAN_SOFTWARE_MEISTER("부산소프트웨어마이스터고등학교"),
    GWANGJU_SOFTWARE_MEISTER("광주소프트웨어마이스터고등학교");

    private final String displayName;
}
