package com.command.itdaserver.global.validation;

import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.user.domain.enums.Major;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Annotation;

public class CustomMajorValidator implements ConstraintValidator<ValidCustomMajor, SignUpRequest> {

    @Override
    public boolean isValid(SignUpRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }

        // major가 ETC일 때만 customMajor 필수 검증
        if (request.major() == Major.ETC) {
            return request.customMajor() != null && !request.customMajor().isBlank();
        }

        return true;
    }
}
