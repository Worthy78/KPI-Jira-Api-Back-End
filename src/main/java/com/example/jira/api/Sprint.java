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
    // I've remark that sometimes this property isn't set in the api response
    private int originBoardId ;
    private String goal ;
    private  List<Issue> sptIssues;
    // getter
/*
    public List<Issue> getsptIssues() {
        if(sptIssues.isEmpty()) {
            System.out.println("ok");
            getAllIssue();
        }
        return sptIssues;
    }
*/
    public void setsptIssues(List<Issue> sptIssues) {
        this.sptIssues = sptIssues;
    }

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


    public List<Issue>  getAllIssue(){
            WebClient client = (new Client()).getClient();
        //System.out.println("HEREEEEE"+id);
        Issue response = client.get()
                    .uri("/rest/agile/1.0/board/" + originBoardId + "/sprint/" + id + "/issue?fields=issuetype,sprint,status,epic,priority,versions,summary,project")
                    .retrieve()
                    .bodyToMono(Issue.class).block();
            setsptIssues(response.getIssues());
            return  sptIssues;
    }

    public int bugs(){
        int nbBugs=0;
        for (Issue issue:sptIssues)
            if (issue.type().equals("bug") || issue.type().equals("boggue")) nbBugs = nbBugs++;
        return nbBugs;
    }

    public int issuesTotalAmount(){
        return  sptIssues.size() ;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", originBoardId=" + originBoardId +
                ", goal='" + goal + '\'' +
                "}";
    }
}
