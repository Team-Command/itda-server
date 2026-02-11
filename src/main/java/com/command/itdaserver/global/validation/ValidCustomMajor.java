package com.command.itdaserver.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomMajorValidator.class)
@Documented
public @interface ValidCustomMajor {
    String message() default "기타 전공 선택 시 전공명을 입력해야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
