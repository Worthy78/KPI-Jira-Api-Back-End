package com.example.jira;

import com.example.jira.api.Report;
import com.example.jira.config.ApplicationProperties;
import com.example.jira.service.BoardService;
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

    private final ProjectService projectsService;
    private final BoardService boardService;
    private final SprintService sprintService;

    InitData(ProjectService projectsService, BoardService boardService, SprintService sprintService) {
        this.projectsService = projectsService;
        this.boardService = boardService;
        this.sprintService = sprintService;
    }

    @Override
    public void run(String... args) {
        projectsService
                .allProjects()
                .flatMap(projectsService::save)
                .flatMap(project -> boardService.allBoardByProjects(project.getId())
                        .flatMap(board -> boardService.save(board.toBuilder().project(project).build()))
                        .flatMap(board -> sprintService.getAllSprint(board.getId())
                                .flatMap(sprint -> {
                                    log.info("getOriginBoardId -------------- {} ", sprint.getOriginBoardId());
                                    Mono <Report> report = sprintService.getReport(sprint.getOriginBoardId(), sprint.getId());
                                    return report.map(report1 -> {
                                        sprintService.save(sprint.setReport(report1).toBuilder().board(board).build());
                                                return Mono.just(sprint);
                                    });

                                })
                        )
                )
                .then(Mono.just(true))
                .subscribe(System.out::println);
    }

}
