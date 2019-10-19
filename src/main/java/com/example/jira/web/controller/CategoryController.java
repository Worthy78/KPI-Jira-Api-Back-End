package com.example.jira.web.controller;

import com.example.jira.domain.Category;
import com.example.jira.repository.CategoryRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @ApiOperation(value = "Récupère L'ensemble des catégories")
    @GetMapping(value = "/category")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
