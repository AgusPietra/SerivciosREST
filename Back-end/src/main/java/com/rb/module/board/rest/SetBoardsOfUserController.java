package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import com.rb.module.common.response.codes.Code;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*****************UNUSED***************/

@CrossOrigin
@RestController
public class SetBoardsOfUserController {


    private BoardService boardService;
    private UserService userService;
    @Autowired
    public SetBoardsOfUserController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }


    @RequestMapping(value = {"/users/{userName}/boards"}, method = RequestMethod.POST)
    public Code setBoards(@PathVariable(value="userName") String userName, @RequestBody Board[] boards) {

        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
        }
        else {
            for (Board board: boards) {
                System.out.println("Setting board: " + board.getBoardName());
                //TODO, implementar si se comienza a utilizar desde el front.
            }
        }
        return new Code(0);
    }
}

