package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddNewFollowedUserOnBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IFollowedUserRepository followedUserRepository;


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/followedusers/{followedUserName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="followedUserName") String followedUserName) {
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            //Primero Chequeo si es un seguido nuevo para la tabla de seguidos únicos.
            FollowedUser user = followedUserRepository.findByUserName(followedUserName);
            if (user == null) {
                user = new FollowedUser(followedUserName);
                user.incrementNumberOfUses();
                followedUserRepository.save(user);//Lo salvo en la tabla de seguidos únicos.
            }
            else{
                user.incrementNumberOfUses();
                followedUserRepository.save(user);//Lo salvo en la tabla de seguidos únicos.//TODO, esto deberia hacerse con updates, no save.
            }

            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo followed metido
                board = new Board(userName, boardName);
            }
            board.setFollowedUser(followedUserName);
            boardRepository.save(board);

            return board;

        }
        return new Board ("","");
    }
}

