package com.example.jira;

import com.example.jira.api.report.Report;
import com.example.jira.config.ApplicationProperties;
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

    @Override
    public void run(String... args) {
       /* categoryService
                .getAllCategories()
                .flatMap(categoryService::save)
                .flatMap(category -> projectsService.allProjects()
        categoryService
                .getAllCategories()
                .flatMap(categoryService::save)
                .subscribe(save ->System.out.println("OK"));*/


        ;
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
                                .flatMap(board -> {
                                            sprintService.getAllSprint(board.getId())
                                                    .flatMap(sprint -> {
                                                        log.info("getOriginBoardId -------------- {} ", sprint.getOriginBoardId());
                                                        Mono<Report> report = sprintService.getReport(sprint.getOriginBoardId(), sprint.getId());
                                           /* return report.map(report1 -> {
                                                sprintService.save(sprint.setReport(report1).toBuilder().board(board).build());
                                                return Mono.just(sprint);
                                            });*/
                                                       /* report.subscribe(report1 -> {
                                                            // sprintService.save(sprint.setReport(report1).toBuilder().board(board).build());
                                                            log.info("REPORT OK {}", report1);
                                                        });*/
                                                        return Mono.just("OK REPORT");
                                                    }).then().subscribe(ok->log.info("REPORT OK {}", ok));

                                            return Mono.just("OK");
                                        }
                                )
                        )
                        .then(Mono.just("true"))
                        .subscribe(project -> log.info("PROJECT GOT {}", project))
                )
                .subscribe(val -> log.info("LAST SUBSCRIBE {}", val));

        /*projectsService
                .allProjects()
                .flatMap(project -> {
                    System.out.println(project);
                    return projectsService.save(project.toBuilder().build());
                })
                /*  .flatMap(project -> boardService.allBoardByProjects(project.getId())
                          .flatMap(board -> boardService.save(board.toBuilder().project(project).build()))
                          .flatMap(board -> sprintService.getAllSprint(board.getId())
                                  .flatMap(sprint -> {
                                      Mono<Report> report = sprintService.getReport(sprint.getOriginBoardId(), sprint.getId());
                                      report.map(theReport -> sprint.setReport(theReport)).subscribe(theSprint -> sprintService.save(theSprint.toBuilder().board(board).build()));
                                      return report;
                                  })
                          )
                  )*/
        /*
                .then(Mono.just(true))
                .subscribe(last -> {
                    System.out.println(last);
                });*/

        /*
        projectsService
                .allProjects()
                .flatMap(projectsService::save)
                .flatMap(project -> boardService.allBoardByProjects(project.getId())
                        .flatMap(board -> boardService.save(board.toBuilder().project(project).build()))
                        .flatMap(board -> sprintService.getAllSprint(board.getId())
                                .flatMap(sprint -> {
                                    log.info("getOriginBoardId -------------- {} ", sprint.getOriginBoardId());
                                    Mono <Report> report = sprintService.getReport(sprint.getOriginBoardId(), sprint.getId());
<<<<<<< HEAD
                                    return report.map(report1 -> {
                                        sprintService.save(sprint.setReport(report1).toBuilder().board(board).build());
                                                return Mono.just(sprint);
                                    });

=======
                                    report.map(theReport -> sprint.setReport(theReport)).subscribe(theSprint ->  sprintService.save(theSprint.toBuilder().board(board).build()));
                                    return report;
>>>>>>> 37cbaa44b8451e1fbb2130487521f886f2ca70fb
                                })
                        )
                )
                .then(Mono.just(true))
                .subscribe(System.out::println);

         */
    }

    private void allCategorie() {
        categoryService
                .getAllCategories()
                .flatMap(cate -> {
                    log.info("CATEGORIE {} ", cate);
                    return categoryService.save(cate);
                })
                .then(Mono.just("true"))
                .map(val -> {
                    log.info("VALUE {} ", val);
                    return val;
                })
                .subscribe(val -> log.info("finish"));
    }

}
