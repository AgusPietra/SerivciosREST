package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
public class GetUserBoardInterestsAndUsersController {

    private BoardService boardService;

    @Autowired
    public GetUserBoardInterestsAndUsersController(BoardService boardService) {
        this.boardService = boardService;
    }

    private String userName = "";
    private String boardName = "";


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.GET)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {

        Board boardExists = this.boardService.findByUserNameAndBoardName(userName, boardName);
        if(boardExists == null) {
            return new Board(userName, "");
        }
        else return boardExists;
    }
}
