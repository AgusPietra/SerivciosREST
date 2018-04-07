package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SetNewBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IUserRepository userRepository;

    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.POST)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {

        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
        }
        else {
            Board boardExists = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (boardExists == null)
                boardRepository.save(new Board(userName, boardName));
            return boardExists;
        }
        return new Board ("","");
    }
}

