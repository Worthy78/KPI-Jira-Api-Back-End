package com.example.jira.service.impl;

import com.example.jira.api.report.Report;
import com.example.jira.config.ApplicationProperties;
import com.example.jira.domain.Sprint;
import com.example.jira.repository.SprintRepository;
import com.example.jira.service.SprintService;
import com.example.jira.service.dto.SprintProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final WebClient webClient;
    private final ApplicationProperties applicationProperties;

    public SprintServiceImpl(com.example.jira.repository.SprintRepository sprintRepository, ApplicationProperties applicationProperties) {
        this.sprintRepository = sprintRepository;
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.builder()
                .baseUrl(this.applicationProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.set(HttpHeaders.AUTHORIZATION, this.applicationProperties.getToken()))
                .build();
    }

    @Override
    public Mono<Report> getReport(int originBoardId, int id) {
        //log.info("SPRINT BEFORE ERROR orgID {} id {} ", originBoardId, id);
        Mono <Report> report =  webClient.get()
                .uri("/rest/greenhopper/1.0/rapid/charts/sprintreport?rapidViewId=" + originBoardId + "&sprintId=" + id )
                .retrieve()
                .bodyToMono(Report.class);
        //log.info("SPRINT AFTER ERROR orgID {} id {} ", originBoardId, id);
                //.doOnError(e ->e.printStackTrace());
       // report.subscribe(thereport -> log.info("REPORT { }", thereport));
        return report;
    }

    public Flux<Sprint> getAllSprint(int id){
        Mono<SprintProcess>  response = webClient.get()
                .uri("rest/agile/1.0/board/"+id+"/sprint?")
                .retrieve()
                .bodyToMono(SprintProcess.class);
        //response.subscribe(sprintProcess -> log.info("SPRINT { }", sprintProcess));

        return response.map(SprintProcess::getValues).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Sprint> save(Sprint sprint) {
        sprintRepository.saveAndFlush(sprint);
        return Mono.just(sprint);
    }


}


