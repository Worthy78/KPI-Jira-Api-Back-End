package com.example.jira.api;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;

public class Sprint  extends ApiResponse<Board> {
    int id ;
    String self;
    String state ;
    String name ;
    Date startDate ;
    Date endDate ;
    int originBoardID ;
    String goal ;

    public  List<Board>  getAllSprint(){
        WebClient client = (new Client() ).getClient() ;
        Sprint  response = client.get()
                .uri("/rest/agile/1.0/board?type=scrum")
                .retrieve()
                .bodyToMono(Sprint.class).block();

        return response.getValues() ;
    }
}
