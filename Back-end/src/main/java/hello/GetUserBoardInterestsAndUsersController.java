package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
public class GetUserBoardInterestsAndUsersController {

    @Autowired
    private IBoardRepository boardRepository;

    private String userName = "";
    private String boardName = "";


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.GET)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {

        Board boardExists = boardRepository.findByUserNameAndBoardName(userName, boardName);
        if(boardExists == null) {
            return new Board(userName, "");
        }
        else return boardExists;
    }
}
