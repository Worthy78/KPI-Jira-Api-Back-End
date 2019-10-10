package com.example.jira.service.dto;
import com.example.jira.api.ApiResponse;
import com.example.jira.api.Client;
import com.example.jira.api.IssueType;
import com.example.jira.api.Report;
import com.example.jira.domain.Sprint;
import lombok.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SprintProcess  {
    private List<Sprint> values ;

    private int id ;
    private String self;
    private String state ;
    private String name ;
    private Date startDate ;
    private Date endDate ;
    // I've remark that sometimes this property isn't set in the api response
    private int originBoardId ;
    private String goal ;
    //  private  List<Issue> sptIssues;

    private Report report ;



/*
    private List<Issue>  getIssue(String jql){
            WebClient client = (new Client()).getClient();
        Issue response = client.get()
                    .uri("/rest/agile/1.0/board/" + originBoardId + "/sprint/" + id + "/issue?fields=issuetype,sprint,status,epic,priority,versions,summary,project&jql="+jql)
                    .retrieve()
                    .bodyToMono(Issue.class).block();
            setSptIssues(response.getIssues());
            return  sptIssues;
    }


   /* public List<Issue>  getAllIssue(){
        return  getIssue("");
    }

    public int usEngage(){
        return  getIssue("type = Story").size();
    }
    public int  usRealise(){
        return  getIssue("type = Story AND status = Done").size();
    }

    public int  bug(){
        return  getIssue("type = Bug").size();
    } */

    public List<IssueType>  issueTypes (){
        List<IssueType> issueTypes = new ArrayList<>();
        WebClient client = (new Client()).getClient() ;
        Flux< IssueType> response = client.get()
                .uri("/rest/api/2/issuetype")
                .retrieve()
                .bodyToFlux(IssueType.class);
        response.subscribe(project -> issueTypes.add(project));
        return  issueTypes;
    }


    public String results (){
        report();
        return  "\n-------------------------------------------------------------------\n" +
                "Sprint : "+name+"\n" +
                "STATUT : " + state +"\n"+
                "Date de Début : " + startDate +"\n"+
                "Date de Fin : " + endDate +"\n"+
                "Nombre d'issues : " + report.nbIssues() +"\n"+
                "BUG " + report.bugs() +"\n"+
                "US Engagé : " + report.usEngage()  +"\n"+
                "US Réalisé : " + report.usRealise()  +"\n"+
                "STP Engagé : " + report.stpEngage()  +"\n"+
                "STP Réalisé : " + report.stpRealise() +"\n"+
                "\n----------------------========KPI========------------------------------\n"+
                "\n-------------------------------------------------------------------\n"
                ;
    }

    private  void report(){
        WebClient client = (new Client()).getClient();

        Report response = client.get()
                .uri("/rest/greenhopper/1.0/rapid/charts/sprintreport?rapidViewId=" + originBoardId + "&sprintId=" + id )
                .retrieve()
                .bodyToMono(Report.class).block();
        this.report = response;
    }

    /*
    public Sprint getSprint(){
        report();
        return new Sprint(id,self,state,name,startDate,endDate,originBoardId,goal,report.stpEngage(),report.stpRealise(),report.nbIssues(),report.usRealise(),report.usEngage());
    }
*/
}
