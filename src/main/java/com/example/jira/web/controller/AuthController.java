package com.example.jira.web.controller;

import com.example.jira.api.Client;
import com.example.jira.api.Credential;
import com.example.jira.api.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Api( description="API pour l'authification.")

@RestController
public class AuthController {

    /*spring.datasource.url=jdbc:mysql://localhost:3306/restapi
    spring.datasource.username=root
    spring.datasource.password=root
    */
    @PostMapping(value = "/authenticate")
    public User authentication( @RequestBody Credential credential) {
        // Encode using basic encoder
        String token ="";
        try{
            final String code = credential.getUsername()+":"+credential.getPassword() ;
           token = Base64.getEncoder().encodeToString(
                    code.getBytes("utf-8"));
        } catch(UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }

        WebClient client = (new Client(token)).getClient();
        User response = client.get()
                .uri("/rest/api/2/user?username="+credential.getUsername())
                .retrieve()
                .bodyToMono(User.class).block();
        response.setToken(token);
    return  response ;
    }
}
