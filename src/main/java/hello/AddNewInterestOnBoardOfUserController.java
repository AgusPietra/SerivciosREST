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
    @Autowired
    private IInterestRepository interestRepository;


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}/interests/{interestName}"}, method = RequestMethod.PUT)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName,
                       @PathVariable(value="interestName") String interestName) {
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar

        }
        else {
            //Primero Chequeo si es un interés nuevo para la tabla de intereses únicos.
            Interest interestExists = interestRepository.findByInterestName(interestName);
            if (interestExists == null) {
                interestRepository.save(new Interest(interestName));//Lo salvo en la tabla de intereses únicos.
            }

            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null) {//Si el board no existe, lo creo con el nuevo interés metido
                board = new Board(userName, boardName);
            }
            board.setInterest(interestName);
            boardRepository.save(board);

            return board;

        }
        return new Board ("","");
    }
}

