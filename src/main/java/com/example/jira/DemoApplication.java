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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({ApplicationProperties.class})
//Configuring Spring Boot to use Java 8 Date/Time converters and UTC Timezone
@EntityScan(basePackageClasses = {
        DemoApplication.class,
        Jsr310JpaConverters.class
})

public class DemoApplication  {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
}


/*
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
                                success -> log.info("GOT  PROJECTS"),
                                error -> {
                                    error.printStackTrace();
                                    log.info("PROJECTS ERROR "+error.getMessage());
                                })
                ).then(Mono.just(true))
                .subscribe(
                        success -> log.info("GOT  CATEGORIES")
                        ,
                        error -> log.info("CATEGORY ERROR : "+error.getMessage())
                );
    }

    @Override
    public void run(String... args) {
       // updateDB();
    }

}


 */