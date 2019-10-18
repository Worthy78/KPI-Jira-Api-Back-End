package com.example.jira.web.controller;

import com.example.jira.domain.Board;
import com.example.jira.domain.Project;
import com.example.jira.domain.Sprint;
import com.example.jira.repository.ProjectRepository;
import com.example.jira.web.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Api(description = "API pour es opérations CRUD sur les Projets.")

@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
public class ProjectController {
    @Autowired
    private
    ProjectRepository projectRepository;

    @GetMapping(value = "/project")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping(value = "/project/category/{id}")
    public List<Project> getProjectsCategory(@PathVariable Long id) {

        List<Project> projects = projectRepository.findByProjectCategoryId(id);
        if (projects == null || projects.size() == 0) throw new ResourceNotFoundException("Categorie", id);
/*
        HashMap<Integer, String> resp = new HashMap<Integer, String>();
        projects.stream().flatMap(
                project -> {
                   List<Board> boardList = BoardController.projectBoards(project.getId());

                   boardList.stream().flatMap(
                           board -> {
                               List<Sprint> sprintList = SprintController.getProjectBoardSprints(board.getId());
                                    return  null;
                           }
                   );
                           return null;
                }
        );*/
        return projects;
    }

    @GetMapping(value = "/project/category/autres")
    public List<Project> getProjectsUnCategorized() {
        return projectRepository.findByProjectCategoryIdIsNull();
    }


}
