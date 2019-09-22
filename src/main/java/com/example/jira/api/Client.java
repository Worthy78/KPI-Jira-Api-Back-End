package com.example.jira.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class Client {
    private  WebClient client ;

    public Client() {
        this.client = WebClient
                .builder()
                .baseUrl("https://rachid.atlassian.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.setBasicAuth("sirabraham2016@gmail.com", "CQ9by9RLrYvfOd3GYgoX9B9A"))
                .build();
    }

    public WebClient getClient() {
        return client;
    }
}
