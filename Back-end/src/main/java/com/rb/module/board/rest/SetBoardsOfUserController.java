package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.IBoardService;
import com.rb.module.common.response.codes.Code;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SetBoardsOfUserController {


    private IBoardService boardService;
    private IUserService userService;
    @Autowired
    public SetBoardsOfUserController(IUserService userService, IBoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    /*****************UNUSED***************/
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

