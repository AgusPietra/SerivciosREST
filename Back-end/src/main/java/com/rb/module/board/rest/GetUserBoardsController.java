package com.rb.module.board.rest;

import java.util.ArrayList;
import java.util.List;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class GetUserBoardsController {

    private IBoardService boardService;

    @Autowired
    public GetUserBoardsController(IBoardService boardService) {
        this.boardService = boardService;
    }

    private String userName = "";
    private String boardName = "";


    @RequestMapping(value = {"/users/{userName}/boards"}, method = RequestMethod.GET)
    public List<Board> board(@PathVariable(value="userName") String userName) {

        List<Board> userBoards = this.boardService.findByUserName(userName);
        return userBoards;
    }
}
