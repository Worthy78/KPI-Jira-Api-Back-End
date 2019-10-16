package com.example.jira.service;

import com.example.jira.api.auth.Credential;
import com.example.jira.service.dto.UserDto;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<UserDto> login( Credential credential , String token);
}
