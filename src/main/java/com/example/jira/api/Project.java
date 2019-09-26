package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class Project {
    private String id;
    private String key ;
    private String description ;
    private String name ;
    private String email;
    private String style;

    public Project(String id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<Board> getAllBoard(){
        WebClient client = (new Client()).getClient() ;

        Board response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum&projectKeyOrId="+key)
                .retrieve()
                .bodyToMono(Board.class).block();

        return response.getValues() ;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
