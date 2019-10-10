package com.example.jira.api.auth;

public class User {
    private String key ;
    private String name ;
    private String emailAddress;
    private String displayName;
    private String token;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

class avatarUrls {

}