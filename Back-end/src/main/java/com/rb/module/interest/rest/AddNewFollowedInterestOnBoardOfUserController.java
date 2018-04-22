package com.rb.module.interest.rest;

import com.rb.module.board.service.BoardService;
import com.rb.module.interest.service.InterestService;
import com.rb.module.board.entity.Board;
import com.rb.module.interest.entity.FollowedInterest;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AddNewFollowedInterestOnBoardOfUserController {

    private UserService userService;
    private BoardService boardService;
    private InterestService interestService;
    @Autowired
    public AddNewFollowedInterestOnBoardOfUserController(UserService userService, BoardService boardService, InterestService interestService) {
        this.userService = userService;
        this.boardService = boardService;
        this.interestService = interestService;
    }


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/followedinterests/{followedInterestName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="followedInterestName") String followedInterestName) {
        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            //Primero Chequeo si es un interés nuevo para la tabla de intereses únicos.
            FollowedInterest interest = this.interestService.findByInterestName(followedInterestName);
            if (interest == null) {
                interest = new FollowedInterest(followedInterestName);
                interest.asked();
                this.interestService.save(interest);//Lo salvo en la tabla de intereses únicos.
            }
            else {
                interest.asked();
                this.interestService.save(interest);//Lo salvo en la tabla de intereses únicos. //TODO, esto deberia hacerse con updates, no save.
            }


            Board board = boardService.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo interés metido
                board = new Board(userName, boardName);
            }
            board.setFollowedInterest(followedInterestName);
            this.boardService.save(board);

            return board;

        }
        return new Board ("","");
    }
}

