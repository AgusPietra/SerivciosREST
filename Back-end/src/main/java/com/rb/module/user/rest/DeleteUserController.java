package com.rb.module.user.rest;

import com.rb.module.board.service.BoardService;
import com.rb.module.board.entity.Board;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DeleteUserController {

    private UserService userService;
    private BoardService boardService;
    @Autowired
    public DeleteUserController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }

    @RequestMapping(value = {"/users/{userName}"}, method = RequestMethod.DELETE)
    public User DeleteUser(@PathVariable(value="userName") String userName) {
        User userExists = this.userService.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
        }
        else {
            List<Board> boards = this.boardService.findByUserName(userName);
            for(Board board: boards){
                //TODO esto deberia hacerse con querys mas optimos, y hacer updates en vez de saves,
                //ver: https://www.mkyong.com/mongodb/spring-data-mongodb-update-document/
                /*List<String> boardInterests = board.getFollowedInterests();
                List<String> boardUsers = board.getFollowedUsers();
                for (String interestName : boardInterests) {
                    Interest interestItem = interestRepository.findByInterestName(interestName);
                    interestItem.decrementNumberOfUses(); //Si devolviera 0, podría borrarlo de la lista de intereses únicos
                    interestRepository.save(interestItem);//Lo actualizo en la tabla de intereses únicos.
                }
                */
                this.boardService.deleteByUserNameAndBoardName(userName, board.getBoardName());
            }
            this.userService.deleteByUserName(userName);
        }
        return new User (userName);
    }
}

