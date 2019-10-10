package com.example.jira.domain;

import com.example.jira.api.Report;
import lombok.*;
import reactor.core.publisher.Mono;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sprintId;

    private int id ;
    private String self;
    private String state ;
    private String name ;
    private String startDate ;
    private String endDate ;
    // I've remark that sometimes this property isn't set in the api response
    private int originBoardId ;
    private String goal ;

    // MAIN ATTRIBUTES
    private int stpEngage ;
    private int stpRealise;
    private int nbIssues;
    private int usRealise;
    private int usEngage;
    private int bugs ;

    public Sprint setReport (Report report){
            stpEngage = report.stpEngage();
            stpRealise = report.stpRealise() ;
            nbIssues = report.nbIssues();
            usRealise = report.usRealise();
            usEngage = report.usEngage() ;
            bugs = report.bugs();
            //System.out.println( toString());
            return this;
    }

    @ManyToOne
    private Board board ;
}
