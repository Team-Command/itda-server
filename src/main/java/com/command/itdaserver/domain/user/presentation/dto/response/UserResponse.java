package com.command.itdaserver.domain.user.presentation.dto.response;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse{
        private String name;
        private String userId;
        private String email;
        private Major major;
        private String customMajor;
        private School school;
        private Grade grade;

        public static UserResponse from(User user){
            return UserResponse.builder()
                    .name(user.getName())
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .major(user.getMajor())
                    .customMajor(user.getCustomMajor())
                    .school(user.getSchool())
                    .grade(user.getGrade())
                    .build();
        }
}
