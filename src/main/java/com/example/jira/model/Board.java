package com.example.jira.model;


import com.example.jira.api.ApiResponse;
import com.example.jira.api.Client;
import com.example.jira.api.SprintProcess;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board  extends ApiResponse<Board> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardId ;

    private int id;
    private String name;
    private String type;

    @OneToMany(mappedBy = "board")
    private List<Sprint> sprint = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Sprint> getSprint() {
        return sprint;
    }

    public void setSprint(List<Sprint> sprint) {
        this.sprint = sprint;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public  List<SprintProcess>  getAllSprint(){
        WebClient client = (new Client() ).getClient() ;
        SprintProcess  response = client.get()
                .uri("rest/agile/1.0/board/"+id+"/sprint?")
                .retrieve()
                .bodyToMono(SprintProcess.class).block();

        return response.getValues() ;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}