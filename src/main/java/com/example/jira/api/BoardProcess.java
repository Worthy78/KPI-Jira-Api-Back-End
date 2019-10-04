package com.example.jira.api;

import com.example.jira.api.ApiResponse;
import com.example.jira.api.Client;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class BoardProcess  extends ApiResponse<BoardProcess> {
    private int id ;
    private String name ;
    private String type ;
    // private Location  location ;

    private List<SprintProcess> sprint =  new ArrayList<>();


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    /*
class Location {
    private int projectId ;
    private String displayName ;
    private String projectName ;
    private String projectKey ;
    private String projectTypeKey ;
    private String avatarURI ;
    private  String name ;

    public int getProjectId() {
        return projectId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getProjectTypeKey() {
        return projectTypeKey;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public String getName() {
        return name;
    }
}
*/
}

