package com.example.jira.model;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Sprint  {
    @Id
    @GeneratedValue
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
