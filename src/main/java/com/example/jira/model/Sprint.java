package com.example.jira.model;

import com.example.jira.api.ApiResponse;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Sprint  extends ApiResponse<Sprint> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sprintId;

    private int id ;
    private String self;
    private String state ;
    private String name ;
    private Date startDate ;
    private Date endDate ;
    // I've remark that sometimes this property isn't set in the api response
    private int originBoardId ;
    private String goal ;

    // MAIN ATTRIBUTES
    private int stpEngage ;
    private int stpRealise;
    private int nbIssues;
    private int usRealise;
    private int usEngage;

    @ManyToOne
    @JoinColumn(name="BOARD_ID")
    private Board board ;

    public Sprint() {
    }

    public Sprint(int id, String self, String state, String name, Date startDate, Date endDate, int originBoardId, String goal, int stpEngage, int stpRealise, int nbIssues, int usRealise, int usEngage) {
        this.id = id;
        this.self = self;
        this.state = state;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.originBoardId = originBoardId;
        this.goal = goal;
        this.stpEngage = stpEngage;
        this.stpRealise = stpRealise;
        this.nbIssues = nbIssues;
        this.usRealise = usRealise;
        this.usEngage = usEngage;
    }

    // getters AND setters
    public int getUsEngage() {
        return usEngage;
    }

    public void setUsEngage(int usEngage) {
        this.usEngage = usEngage;
    }

    public int getStpEngage() {
        return stpEngage;
    }

    public void setStpEngage(int stpEngage) {
        this.stpEngage = stpEngage;
    }

    public int getStpRealise() {
        return stpRealise;
    }

    public void setStpRealise(int stpRealise) {
        this.stpRealise = stpRealise;
    }

    public int getNbIssues() {
        return nbIssues;
    }

    public void setNbIssues(int nbIssues) {
        this.nbIssues = nbIssues;
    }

    public int getUsRealise() {
        return usRealise;
    }

    public void setUsRealise(int usRealise) {
        this.usRealise = usRealise;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
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


/*    public int bugs(){
        int nbBugs=0;
        for (Issue issue:sptIssues)
            if (issue.type().equals("bug") || issue.type().equals("boggue")) nbBugs = nbBugs++;
        return nbBugs;
    }

    public int issuesTotalAmount(){
        return  sptIssues.size() ;
    }
*/
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
