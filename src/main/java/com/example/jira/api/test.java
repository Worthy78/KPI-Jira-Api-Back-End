package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        // Getting all projects
        List<ProjectProcess> projects = new ArrayList<>();
        WebClient client = (new Client()).getClient() ;
        Flux< ProjectProcess> response = client.get()
                .uri("/rest/api/2/project?")
                .retrieve()
                .bodyToFlux(ProjectProcess.class);
        response.subscribe(project -> System.out.println("TEST"+project));

        System.out.println("\n\nPROJECTS :" + response);


        ProjectProcess aProject = new ProjectProcess("28","ESHOPB2C","Eshop B2C Board");
        //Project aProject = new Project("10002","EDPS","Exemple de projet Scrum");
        // Getting Boards of a project
        //List<Board> boardList = projects.get(0).getAllBoard();;
        List<BoardProcess> boardList = aProject.getAllBoard();;
        System.out.println("BOARD LIST :");
        for (BoardProcess board : boardList) {
            System.out.println("\n"+board);
        }

        // Getting Sprint of the first board of the project
        System.out.println("BOARD SPRINT  LIST:  ");
        List<SprintProcess> boardSprintList = boardList.get(0).getAllSprint();
        System.out.println(boardSprintList.size() +" boardSprintList :  ");
        int count=-1;
        for (SprintProcess temp : boardSprintList) {
            count++;
            System.out.println("COUNT : "+count+"\n"+temp);
        }

        // issues of the first sprint
       // ISSUE
       // List<Issue> boardSprintIssueList = boardSprintList.get(10).getAllIssue();
/*
        List<Issue> boardSprintIssueList = boardSprintList.get(10).getAllIssue();
        System.out.println("BOARD SPRINT ISSUE LIST: "+boardSprintIssueList.size()+"\n");
        for (Issue temp : boardSprintIssueList) {
            System.out.println("\n"+temp);
        }*/
        for (SprintProcess temp : boardSprintList) {
            System.out.println(temp.results());
        }
      //  System.out.println(boardSprintList.get(10).issueTypes());


    }
}
