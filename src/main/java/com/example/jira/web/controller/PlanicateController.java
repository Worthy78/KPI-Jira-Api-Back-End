package com.example.jira.web.controller;

import com.example.jira.service.impl.UpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Api( description="API pour la planification dela mise à jour de la base de données.")
@RestController
public class PlanicateController{
    public final UpdateService updateService;

    public PlanicateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    // Fonction setTimeOut :
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    @ApiOperation(value = "This plans the database updater for 10 minutes")
    @PutMapping(value = "/planicate")
    public void setUpdateService(String... args) {
        setTimeout(updateService::updateDB, 10000);
        //setTimeout(() -> updateService.updateDB(), 1000);
        //updateService.updateDB();
    }
}
