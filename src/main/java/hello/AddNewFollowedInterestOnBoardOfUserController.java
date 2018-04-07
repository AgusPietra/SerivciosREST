package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddNewFollowedInterestOnBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IFollowedInterestRepository followedInterestRepository;


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/followedinterests/{followedInterestName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="followedInterestName") String followedInterestName) {
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            //Primero Chequeo si es un interés nuevo para la tabla de intereses únicos.
            FollowedInterest interest = followedInterestRepository.findByInterestName(followedInterestName);
            if (interest == null) {
                interest = new FollowedInterest(followedInterestName);
                interest.incrementNumberOfUses();
                followedInterestRepository.save(interest);//Lo salvo en la tabla de intereses únicos.
            }
            else {
                interest.incrementNumberOfUses();
                followedInterestRepository.save(interest);//Lo salvo en la tabla de intereses únicos. //TODO, esto deberia hacerse con updates, no save.
            }


            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo interés metido
                board = new Board(userName, boardName);
            }
            board.setFollowedInterest(followedInterestName);
            boardRepository.save(board);

            return board;

        }
        return new Board ("","");
    }
}

