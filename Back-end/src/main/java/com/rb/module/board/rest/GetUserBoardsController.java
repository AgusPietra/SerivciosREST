package com.rb.module.board.rest;

import java.util.ArrayList;
import java.util.List;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class GetUserBoardsController {

    private BoardService boardService;

    @Autowired
    public GetUserBoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

    private String userName = "";
    private String boardName = "";


    @RequestMapping(value = {"/users/{userName}/boards"}, method = RequestMethod.GET)
    public List<Board> board(@PathVariable(value="userName") String userName) {

        List<Board> userBoards = this.boardService.findByUserName(userName);
//        List<String> boards= new ArrayList<>();

//        for(Board board : userBoards) {
//            boards.add(board.getBoardName());
//        }

        return userBoards;
    }
}
