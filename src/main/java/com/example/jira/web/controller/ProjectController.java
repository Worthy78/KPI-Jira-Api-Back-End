package com.example.jira.web.controller;

import com.example.jira.domain.Project;
import com.example.jira.repository.ProjectRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api( description="API pour es opérations CRUD sur les Projets.")

@RestController
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping(value = "/project")
    public List<Project> getProjects() {
       return  projectRepository.findAll();
    }
}
