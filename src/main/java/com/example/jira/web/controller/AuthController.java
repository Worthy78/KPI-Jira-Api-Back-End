package com.example.jira.web.controller;

import com.example.jira.api.auth.Client;
import com.example.jira.api.auth.Credential;
import com.example.jira.service.AuthService;
import com.example.jira.service.dto.UserDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Api( description="API pour l'authification.")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/authenticate")
    public UserDto authentication(@RequestBody Credential credential) {
        // Encode using basic encoder
        String token ="";
        try{
            final String code = credential.getUsername()+":"+credential.getPassword() ;
           token = Base64.getEncoder().encodeToString(
                    code.getBytes("utf-8"));
        } catch(UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }

       // response.setToken(token);
    return  authService.login(credential,token).block() ;
    }
}
