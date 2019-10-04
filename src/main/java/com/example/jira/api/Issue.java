package com.example.jira.api;

public class Issue extends ApiResponse<Issue> {
   /* private String expand ;*/
    private  int id ;
    private String key ;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

}

