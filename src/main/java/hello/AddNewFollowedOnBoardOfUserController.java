package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddNewFollowedOnBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IFollowedRepository followedRepository;


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/following/{followedName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="followedName") String followedName) {
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            //Primero Chequeo si es un seguido nuevo para la tabla de seguidos únicos.
            Followed followedExists = followedRepository.findByFollowedName(followedName);
            if (followedExists == null) {
                followedRepository.save(new Followed(followedName));//Lo salvo en la tabla de seguidos únicos.
            }

            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo followed metido
                board = new Board(userName, boardName);
            }
            board.setFollowing(followedName);
            boardRepository.save(board);

            return board;

        }
        return new Board ("","");
    }
}

