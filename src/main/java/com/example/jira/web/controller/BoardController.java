package com.example.jira.web.controller;

import com.example.jira.repository.BoardRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api( description="API pour es opérations CRUD sur les Boards.")

@RestController
public class BoardController {
    @Autowired
    private BoardRepository boardDao;

    /*public BoardController() {
        ProjectProcess aProject = new ProjectProcess("28","ESHOPB2C","Eshop B2C Board");
        List<BoardProcess> boardList = aProject.getAllBoard();
        BoardProcess aBaordProcess = boardList.get(0) ;
        Board aBaord = new Board() ;
        aBaord.setId(aBaord.getId());
        aBaord.setName(aBaord.getName());
        addBoard(aBaord);
    }*/

}
