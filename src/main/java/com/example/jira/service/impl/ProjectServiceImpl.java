package com.example.jira.service.impl;

import com.example.jira.api.IssueType;
import com.example.jira.api.Report;
import com.example.jira.model.Board;
import com.example.jira.model.Project;
import com.example.jira.service.ProjectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final WebClient client ;
    private final String baseUrl = "http://jira.tools.orange-sonatel.com";
    public ProjectServiceImpl() {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.set(HttpHeaders.AUTHORIZATION, "Basic c3RnX2VwdF9kczplcHQyMDE5"))
                .build();;
    }


    @Override
    public Flux<Board> getAllBoard(String id) {
        Board response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum&projectKeyOrId="+id)
                .retrieve()
                .bodyToMono(Board.class).block();

        return Flux.fromIterable(response.getValues()) ;
    }

    @Override
    public Flux<Project> getAllProject() {
        Flux<Project> response = client.get()
                .uri("/rest/api/2/project")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Project.class));
        return response;
    }

    public Report report(int originBoardId, int id){
         return client.get()
                .uri("/rest/greenhopper/1.0/rapid/charts/sprintreport?rapidViewId=" + originBoardId + "&sprintId=" + id )
                .retrieve()
                .bodyToMono(Report.class).block();
    }
/*
    public IssueType issueType(){
        return client.get()
                .uri("/rest/api/2/issuetype")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(IssueType.class));
    }
 */
}
