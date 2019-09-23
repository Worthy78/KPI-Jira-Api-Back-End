package com.example.jira.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private  WebClient client ;

    public Client() {
        this.client = WebClient
                .builder()
                .baseUrl("https://professionnelpro.atlassian.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.setBasicAuth("professionnelpro77@gmail.com", "dS7lVghDm111wUtMJojD0FB6"))
                .build();
    }

    public List<Project> getAllProject(){
        WebClient client = (new Client()).getClient() ;
        List <Project> response = new ArrayList<>();
        response = client.get()
                .uri("/rest/api/2/project")
                .retrieve()
                .bodyToMono(response.getClass()).block();
        return response ;
    }

    public WebClient getClient() {
        return client;
    }
}
