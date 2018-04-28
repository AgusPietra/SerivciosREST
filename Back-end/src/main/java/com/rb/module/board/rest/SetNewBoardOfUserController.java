package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import com.rb.module.common.response.codes.Code;
import com.rb.module.interest.service.InterestService;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SetNewBoardOfUserController {


    private BoardService boardService;
    private UserService userService;
    private InterestService interestService;
    @Autowired
    public SetNewBoardOfUserController(UserService userService, BoardService boardService, InterestService interestService) {
        this.userService = userService;
        this.boardService = boardService;
        this.interestService = interestService;
    }


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.POST)
    public Code setNewboard(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName
            , @RequestBody Board board) {

        System.out.println("Saving new board");
        System.out.println("user name: " + userName);
        System.out.println("board name: " + boardName);
        System.out.println("Saving new board named: " + board.getUserName() + " from user: " + board.getBoardName());

        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            Board exsistingBoard = this.boardService.findByUserNameAndBoardName(userName, boardName);

            if (exsistingBoard != null)
                this.boardService.deleteByUserNameAndBoardName(userName, boardName);
            this.boardService.save(board);
            this.interestService.setNewInterests(board.interests);
            System.out.println("Saved new board named: " + board.getBoardName() + " from user: " + board.getUserName());

        }
        return new Code(0);
    }
}

