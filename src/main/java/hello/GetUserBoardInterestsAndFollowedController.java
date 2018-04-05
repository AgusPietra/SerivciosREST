package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserBoardInterestsAndFollowedController {

    @Autowired
    private IBoardRepository boardRepository;

    private String userName = "";
    private String boardName = "";


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.GET)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {

        Board boardExists = boardRepository.findByUserNameAndBoardName(userName, boardName);
        if(boardExists == null) {
            List<String> interests = new ArrayList<>();
            List<String> following = new ArrayList<>();
            return new Board(userName, "", interests, following);
        }
        else return boardExists;
    }
}
