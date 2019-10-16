package com.example.jira.api.auth;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.*;
import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key ;
    private String name ;
    private String emailAddress;
    private String displayName;
    private HashMap <String,String> avatarUrls ;
    private String token;

}

