package com.example.jira.service.impl;

import com.example.jira.api.auth.Credential;
import com.example.jira.config.ApplicationProperties;
import com.example.jira.service.AuthService;
import com.example.jira.service.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final ApplicationProperties applicationProperties;

    private WebClient webClient;

    public AuthServiceImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public Mono<UserDto> login(Credential credential,String token) {
        return  WebClient.builder()
                .baseUrl(this.applicationProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.set(HttpHeaders.AUTHORIZATION, "Basic "+token))
                .build()
                .get()
                .uri("/rest/api/2/user?username="+credential.getUsername())
                .retrieve()
                .bodyToMono(UserDto.class) ;
    }
}
