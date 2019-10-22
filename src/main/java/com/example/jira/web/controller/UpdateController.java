package com.example.jira.web.controller;


import com.example.jira.service.impl.UpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Api( description="API pour la mise à jour de la base de données.")
@RestController
public class UpdateController implements CommandLineRunner {

    public final UpdateService updateService;

    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @ApiOperation(value = "This updates the database")
    @PutMapping(value = "/update")
    // Running the script
    @Override
    public void run(String... args) {
        updateService.updateDB();
    }
}
