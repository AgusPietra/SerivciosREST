package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import com.rb.module.common.response.codes.Code;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class DeleteBoardOfUserController {

    private UserService userService;
    private BoardService boardService;
    @Autowired
    public DeleteBoardOfUserController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.DELETE)
    public Code deleteBoard(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {
        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
        }
        else {
            Board board = this.boardService.findByUserNameAndBoardName(userName, boardName);

            if (board != null) {
                this.boardService.deleteByUserNameAndBoardName(userName, boardName);
            }
        }
        return new Code(0);
    }
}

