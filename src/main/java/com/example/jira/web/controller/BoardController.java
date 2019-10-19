package com.example.jira.web.controller;

import com.example.jira.domain.Board;
import com.example.jira.repository.BoardRepository;
import com.example.jira.web.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api( description="API pour es opérations CRUD sur les Boards.")
@RestController
public class BoardController {
    @Autowired
    private   BoardRepository  boardRepository;

    @GetMapping(path = "/board/{id}")
    public Board getBoards(@PathVariable String id) {
        Optional<Board> board = boardRepository.findById(id);
        //System.out.println(boards);

        return board.get();
    }

    @GetMapping(value = "/boards/project/{id}")
    public  List<Board> projectBoards(@PathVariable String id)   {

        List<Board> boards = boardRepository.findByProjectId(id);
        return  boards;
    }


}
