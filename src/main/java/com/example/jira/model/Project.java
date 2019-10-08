package com.example.jira.model;

import com.example.jira.api.BoardProcess;
import com.example.jira.api.Client;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int projectId;

    private String id;
    private String name ;

    @OneToMany(mappedBy = "project")
    private List<Board> board =  new ArrayList<>();

    public Project(String id, String key, String name) {
        this.id = id;
        //this.key = key;
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public Project() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Board> getAllBoard(){
        WebClient client = (new Client()).getClient() ;
        Board response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum&projectKeyOrId="+id)
                .retrieve()
                .bodyToMono(Board.class).block();
        return response.getValues() ;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", board=" + board +
                '}';
    }
}
