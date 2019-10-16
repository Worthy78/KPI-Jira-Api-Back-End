package com.example.jira.service.dto;

import com.example.jira.domain.Board;
import com.example.jira.domain.Sprint;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SprintDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id ;
    private String self;
    private String state ;
    private String name ;
    private Date startDate ;
    private Date endDate ;
    private int originBoardId ;
    private String goal ;

    private List<SprintDto> values ;

}
