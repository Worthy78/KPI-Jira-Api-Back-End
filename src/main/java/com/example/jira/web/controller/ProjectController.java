package com.example.jira.web.controller;

import com.example.jira.domain.Project;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api( description="API pour es opérations CRUD sur les Projets.")

@RestController
public class ProjectController {
    /*
    @PostMapping  (value = "/Produits")
    public void updateProduit(@RequestBody Project project) {

        productDao.save(product);
    }*/
}
