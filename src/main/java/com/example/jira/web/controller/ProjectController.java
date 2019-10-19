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
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping(value = "/project")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping(value = "/project/category/{id}")
    public List<Project> getProjectsCategory(@PathVariable Long id) {
        List<Project> projects = projectRepository.findByProjectCategoryId(id);
        return projects;
    }
/*
    @GetMapping(value = "/project/category/{id}/page/{pageNumber}")
    public Page<Project> getProjectsCategory(@PathVariable Long id , @PathVariable int  pageNumber) {
        Pageable pages = PageRequest.of(pageNumber, 2);
        Page<Project> projects = projectRepository.findByProjectCategoryId(id,pages);
        //if (projects == null || !projects.hasContent()) throw new ResourceNotFoundException("Categorie", id);
        return projects;
    }*/
    @GetMapping(value = "/project/category/autres")
    public List<Project> getProjectsUnCategorized() {
        return projectRepository.findByProjectCategoryIdIsNull();
    }


}
