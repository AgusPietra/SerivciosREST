package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SetNewBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;

    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.POST)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {

        Board boardExists = boardRepository.findByUserNameAndBoardName(userName, boardName);
        List<String> interests = new ArrayList<>();
        List<String> following = new ArrayList<>();

        //TODO chequear que el usuario exista.

        if(boardExists == null)
            boardRepository.save(new Board(userName, boardName, interests, following));
        return new Board(userName, boardName, interests, following);
    }
}

