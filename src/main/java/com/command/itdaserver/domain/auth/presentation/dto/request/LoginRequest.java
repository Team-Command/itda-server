package com.command.itdaserver.domain.auth.presentation.dto.request;


public record LoginRequest (
        String userId,
        String password
){
}
