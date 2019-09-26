package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
/*        HttpResponse<JsonNode> response = Unirest.get("https://sambasg7.atlassian.net/rest/agile/1.0/sprint/2")
                        .basicAuth("sougousamba@gmail.com", "DARsOdtDKiu1iev0pReXC8D9")
                        .header("Accept", "application/json")
                        .asJson();
                System.out.println(response.getBody());
         }
         ("sirabraham2016@gmail.com", "CQ9by9RLrYvfOd3GYgoX9B9A")
*/

        // Getting all projects
        List<Project> projects = new ArrayList<>();
        WebClient client = (new Client()).getClient() ;
        Flux< Project> response = client.get()
                .uri("/rest/api/2/project")
                .retrieve()
                .bodyToFlux(Project.class);
        response.subscribe(project -> projects.add(project));

        System.out.println("PROJECTS :" + projects);

        Project aProject = new Project("10002","EDPS","Exemple de projet Scrum");
        // Getting Boards of a project
        //List<Board> boardList = projects.get(0).getAllBoard();;
        List<Board> boardList = aProject.getAllBoard();;
        System.out.println("BOARD LIST :");
        for (Board temp : boardList) {
            System.out.println(temp);
        }

        // Getting Sprint of the first board of the project
        System.out.println("BOARD SPRINT  LIST:  ");
        List<Sprint> boardSprintList = boardList.get(0).getAllSprint();
        System.out.println(boardSprintList.size() +" boardSprintList :  ");
        for (Sprint temp : boardSprintList) {
            System.out.println(temp);
        }

        // issues of the first sprint
        System.out.println("BOARD SPRINT ISSUE LIST:  ");
       // ISSUE
        List<Issue> boardSprintIssueList = boardSprintList.get(0).getAllIssue();
        System.out.println(boardSprintIssueList.size() +" boardSprintIssueList :  ");
        for (Issue temp : boardSprintIssueList) {
            System.out.println(temp);
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
