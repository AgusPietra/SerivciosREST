package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddNewInterestOnBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IUserRepository userRepository;

    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/interests/{interestName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="interestName") String interestName) {
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo interés metido
                board = new Board(userName, boardName);
            }
            board.setInterest(interestName);
            boardRepository.save(board);

            //TODO, chequear la lista de intereses únicos para ver si hay que agregarlo.

            return board;

        }
        return new Board ("","");
    }
}

