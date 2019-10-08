package com.example.jira.service;

import com.example.jira.api.Report;
import com.example.jira.model.Board;
import com.example.jira.model.Project;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProjectService {
     Flux<Board> getAllBoard(String id) ;
     Flux<Project> getAllProject();
     Report report(int originBoardId, int id);
}
