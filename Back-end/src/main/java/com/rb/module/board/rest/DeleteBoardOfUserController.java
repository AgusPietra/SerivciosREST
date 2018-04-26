package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
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
    public Board deleteBoard(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {
        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
        }
        else {
            Board board = this.boardService.findByUserNameAndBoardName(userName, boardName);

            if (board == null)
                board = new Board(userName, boardName);
            else {
                //TODO esto deberia hacerse con querys mas optimos, y hacer updates en vez de saves,
                //ver: https://www.mkyong.com/mongodb/spring-data-mongodb-update-document/
/*                List<String> boardInterests = board.getFollowedInterests();
                List<String> boardUsers = board.getFollowedUsers();
                for (String interestName : boardInterests) {
                    Interest interestItem = interestRepository.findByInterestName(interestName);
                    interestItem.decrementNumberOfUses(); //Si devolviera 0, podría borrarlo de la lista de intereses únicos
                    followedInterestRepository.save(interestItem);//Lo actualizo en la tabla de intereses únicos.
                }
                */
                this.boardService.deleteByUserNameAndBoardName(userName, boardName);
            }
            return board;
        }
        return new Board ("","");
    }
}

