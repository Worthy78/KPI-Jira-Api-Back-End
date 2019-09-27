package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {

        // Getting all projects
        List<Project> projects = new ArrayList<>();
        WebClient client = (new Client()).getClient() ;
        Flux< Project> response = client.get()
                .uri("/rest/api/2/project")
                .retrieve()
                .bodyToFlux(Project.class);
        response.subscribe(project -> projects.add(project));

        System.out.println("\n\nPROJECTS :" + projects);

        Project aProject = new Project("28","ESHOPB2C","Eshop B2C Board");
        // Getting Boards of a project
        //List<Board> boardList = projects.get(0).getAllBoard();;
        List<Board> boardList = aProject.getAllBoard();;
        System.out.println("BOARD LIST :");
        for (Board board : boardList) {
            System.out.println("\n"+board);
        }

        // Getting Sprint of the first board of the project
        System.out.println("BOARD SPRINT  LIST:  ");
        List<Sprint> boardSprintList = boardList.get(0).getAllSprint();
        System.out.println(boardSprintList.size() +" boardSprintList :  ");
        int count=-1;
        for (Sprint temp : boardSprintList) {
            count++;
            System.out.println("COUNT : "+count+"\n"+temp);
        }

        // issues of the first sprint
       // ISSUE
        List<Issue> boardSprintIssueList = boardSprintList.get(10).getAllIssue();
        System.out.println("BOARD SPRINT ISSUE LIST: "+boardSprintIssueList.size()+"\n");
        for (Issue temp : boardSprintIssueList) {
            System.out.println("\n"+temp);
        }
 /*
        List<Project> projects = (new Client()).getAllProject();
        System.out.println(" PROJECTS ! :  ");
        for (Project temp : projects) {
            System.out.println(temp);
        }

        WebClient client = (new Client()).getClient() ;

        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("aaa","bbb"));

        List<String> list = new ArrayList<>();

        Flux< Project> response = client.get()
                .uri("/rest/api/2/project")
                .retrieve()
                .bodyToFlux(Project.class);
        stringFlux.subscribe(val -> list.add(val));

        System.out.println(list);
  */

    }
}
