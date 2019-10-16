package com.example.jira;

import com.example.jira.api.report.Report;
import com.example.jira.config.ApplicationProperties;
import com.example.jira.domain.Sprint;
import com.example.jira.service.BoardService;
import com.example.jira.service.CategoryService;
import com.example.jira.service.ProjectService;
import com.example.jira.service.SprintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({ApplicationProperties.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
		/*
		Project aProject = new Project("10002","EDPS","Exemple de projet Scrum");
		aProject = ProjectDao.save(aProject);
        */

@Component
@Slf4j
class InitData implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ProjectService projectsService;
    private final BoardService boardService;
    private final SprintService sprintService;

    InitData(CategoryService categoryService, ProjectService projectsService, BoardService boardService, SprintService sprintService) {
        this.categoryService = categoryService;
        this.projectsService = projectsService;
        this.boardService = boardService;
        this.sprintService = sprintService;
    }

    public void updateDB() {
        categoryService
                .getAllCategories()
                .flatMap(categoryService::save)
                .then(Mono.just("true"))
                .map(val -> projectsService
                        .allProjects()
                        .flatMap(projectsService::save)
                        // NOW POPULATING THE BD
                        .flatMap(project -> boardService
                                .allBoardByProjects(project.getId())

                                .flatMap(board -> boardService.save(board.toBuilder().project(project).build()))
                                .flatMap(board -> sprintService.getAllSprint(board.getId())
                                        .map(sprint -> sprintService.getReport(sprint.getOriginBoardId(), sprint.getId())
                                                .map(report -> {
                                                    Sprint theSprint = sprint.setReport(report);
                                                    theSprint.kpi();
                                                    sprintService.save(theSprint.toBuilder().board(board).build());
                                                    return Mono.just(true);
                                                }).subscribe()
                                        )
                                )
                        )
                        .then(Mono.just("true"))
                        .subscribe(
                                success -> {
                                    log.info("GOT  PROJECTS");
                                },
                                error -> {
                                    error.printStackTrace();
                                    log.info("PROJECTS ERROR "+error.getMessage());
                                })
                ).then(Mono.just(true))
                .subscribe(
                        success -> {
                            log.info("GOT  CATEGORIES");
                        },
                        error -> {
                            log.info("CATEGORY ERROR : "+error.getMessage());
                        }
                );
    }

    @Override
    public void run(String... args) {
        updateDB();
    }


}
