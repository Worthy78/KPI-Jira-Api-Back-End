package com.example.jira.web.controller;

import com.example.jira.domain.Board;
import com.example.jira.repository.BoardRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api( description="API pour es opérations CRUD sur les Boards.")

@RestController
public class BoardController {
    @Autowired
    private BoardRepository  boardRepository;

    @GetMapping(path = "/boards")
    public List<Board> getBoards() {
        List<Board> boards = boardRepository.findAll();
        //System.out.println(boards);

        return boards;
    }

}
