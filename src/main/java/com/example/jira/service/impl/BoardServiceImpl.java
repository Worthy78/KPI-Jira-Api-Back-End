package com.example.jira.service.impl;

import com.example.jira.config.ApplicationProperties;
import com.example.jira.domain.Board;
import com.example.jira.domain.Sprint;
import com.example.jira.repository.BoardRepository;
import com.example.jira.service.BoardService;
import com.example.jira.service.dto.BoardProcess;
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
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final ApplicationProperties applicationProperties;

    private WebClient webClient;

    public BoardServiceImpl(BoardRepository boardRepository, ApplicationProperties applicationProperties) {
        this.boardRepository = boardRepository;
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.builder()
                .baseUrl(this.applicationProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeaders(header -> header.set(HttpHeaders.AUTHORIZATION, this.applicationProperties.getToken()))
                .build();
    }

    @Override
    public Flux<Board> allBoardByProjects(String id) {
        Mono<BoardProcess> response = webClient.get()
                .uri("/rest/agile/1.0/board?type=scrum&projectKeyOrId="+id)
                .retrieve()
                .bodyToMono(BoardProcess.class);
        //response.subscribe(boardProcess -> log.info("BOARD {", boardProcess));

        return response.map(BoardProcess::getValues).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Board> save(Board board) {
        boardRepository.saveAndFlush(board);
        return Mono.just(board);
    }
}
