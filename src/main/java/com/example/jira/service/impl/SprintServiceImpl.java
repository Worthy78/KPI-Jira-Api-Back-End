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
        final String  url = "/rest/greenhopper/1.0/rapid/charts/sprintreport?rapidViewId=" + originBoardId + "&sprintId=" + id;
        Mono <Report> report =  webClient.get()
                .uri(url )
                .retrieve()
                .bodyToMono(Report.class)
                .doOnError(e -> {
                    log.info("ERROR URL -- {}",this.applicationProperties.getBaseUrl()+url);
                    Mono.empty();});
                //.doOnError(e ->e.printStackTrace());
        return report;
    }

    public Flux<Sprint> getAllSprint(int id){
        Mono<SprintProcess>  response = webClient.get()
                .uri("rest/agile/1.0/board/"+id+"/sprint?")
                .retrieve()
                .bodyToMono(SprintProcess.class);

        return response.map(SprintProcess::getValues).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Sprint> save(Sprint sprint) {
        sprintRepository.saveAndFlush(sprint);
        return Mono.just(sprint);
    }


}


