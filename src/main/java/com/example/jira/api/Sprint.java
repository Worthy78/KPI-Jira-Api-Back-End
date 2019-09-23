package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;

public class Sprint  extends ApiResponse<Sprint> {
    private int id ;
    private String self;
    private String state ;
    private String name ;
    private Date startDate ;
    private Date endDate ;
    private int originBoardId ;
    private String goal ;

    // getter
    public int getId() {
        return id;
    }

    public String getSelf() {
        return self;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getOriginBoardId() {
        return originBoardId;
    }

    public String getGoal() {
        return goal;
    }

        public  List<Issue>  getAllIssue(){
        WebClient client = (new Client() ).getClient() ;
       Issue  response = client.get()
                .uri("/rest/agile/1.0/board/"+originBoardId+"/sprint/"+id+"/issue?fields=issuetype,sprint,status,epic,priority,versions,summary,customfield_10104")
                .retrieve()
                .bodyToMono(Issue.class).block();
        return response.getIssues() ;
    }

    @Override
    public String toString() {
        return "id: "+id+" name : "+name + " Debut : "+startDate+" Fin : "+endDate+" State : "+state;
    }

}
