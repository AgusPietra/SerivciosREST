package com.rb.module.interest.rest;

import com.rb.module.board.service.BoardService;
import com.rb.module.interest.service.InterestService;
import com.rb.module.board.entity.Board;
import com.rb.module.interest.entity.FollowedUser;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AddNewFollowedUserOnBoardOfUserController {

    private UserService userService;
    private BoardService boardService;
    private InterestService interestService;
    @Autowired
    public AddNewFollowedUserOnBoardOfUserController(UserService userService, BoardService boardService, InterestService interestService) {
        this.userService = userService;
        this.boardService = boardService;
        this.interestService = interestService;
    }


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/followedusers/{followedUserName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="followedUserName") String followedUserName) {
        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            //Primero Chequeo si es un seguido nuevo para la tabla de seguidos únicos.
            FollowedUser user = this.interestService.findByUserName(followedUserName);
            if (user == null) {
                user = new FollowedUser(followedUserName);
                user.asked();
                this.interestService.save(user);//Lo salvo en la tabla de seguidos únicos.
            }
            else{
                user.asked();
                this.interestService.save(user);//Lo salvo en la tabla de seguidos únicos.//TODO, esto deberia hacerse con updates, no save.
            }

            Board board = this.boardService.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo followed metido
                board = new Board(userName, boardName);
            }
            board.setFollowedUser(followedUserName);
            this.boardService.save(board);

            return board;

        }
        return new Board ("","");
    }
}

