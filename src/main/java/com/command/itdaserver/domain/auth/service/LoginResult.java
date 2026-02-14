package com.command.itdaserver.domain.auth.service;

public record LoginResult (
        String sessionId,
        String rememberMeToken
){
}
