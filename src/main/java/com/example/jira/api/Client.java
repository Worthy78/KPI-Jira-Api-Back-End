package com.example.jira.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Client {
    private  WebClient client ;

    public Client() {

            String baseUrl = "http://jira.tools.orange-sonatel.com/";
            // Encode using basic encoder
           /* String token = Base64.getEncoder().encodeToString(
                    "username:pwd".getBytes("utf-8")); */

            this.client = WebClient
                    .builder()
                    .baseUrl(baseUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeaders(header -> header.set(HttpHeaders.AUTHORIZATION, "Basic c3RnX2VwdF9kczplcHQyMDE5"))
                    .build();
        /*} catch(UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }*/
    }

   /* public List<Project> getAllProject(){
        WebClient client = (new Client()).getClient() ;

        Flux< Project> response = client.get()
                .uri("/rest/api/2/project")
                .retrieve()
                .bodyToFlux(Project.class);
        return response ;
    } */

    public WebClient getClient() {
        return client;
    }
}
