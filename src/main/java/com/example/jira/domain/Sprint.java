package com.example.jira.domain;

import com.example.jira.api.report.Report;
import com.fasterxml.jackson.annotation.*;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIdentityReference(alwaysAsId = true)

public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id ;
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // private Long sprintId;

    private String self;
    private String state ;
    private String name ;

    //@JsonFormat(shape= JsonFormat.Shape.STRING , pattern = "dd-MM-yy hh:mm:ss")
    private Date startDate ;

    private Date endDate ;
    private int originBoardId ;
    private String goal ;

    // MAIN ATTRIBUTES
    private int stpEngage ;
    private int stpRealise;
    private int nbIssues;
    private int usRealise;
    private int usEngage;
    private int bugs ;

    // KPIs
    private int completude ;
    private int acceptanceUs ;
    private int acceleration ;
    private int velocite;
    private int dureeSprint;
    private int dureeEntreDeuxSprint;

    public Sprint setReport (Report report){
            stpEngage = report.stpEngage();
            stpRealise = report.stpRealise() ;
            nbIssues = report.nbIssues();
            usRealise = report.usRealise();
            usEngage = report.usEngage() ;
            bugs = report.bugs();
            return this;
    }

    public void kpi (){
        if(stpEngage>0)
        completude = (int) Math.round((double)stpRealise/stpEngage*100 );
        if(usRealise>0)
        acceptanceUs =(int) Math.round((double)usRealise/usEngage*100 );
        //dureeSprint =
    }

    @ManyToOne
    @JoinColumn
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Board board ;
}
