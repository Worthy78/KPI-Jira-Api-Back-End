package com.example.jira.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private String id;
    private String key ;
    private String description ;
    private String name ;
    private String email;
    private String style;

    @OneToMany(mappedBy = "project")
    private List<Board> board =  new ArrayList<>();

    public Project(String id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

    public Project() {
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
