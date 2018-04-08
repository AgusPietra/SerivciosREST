package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeleteBoardOfUserController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IFollowedUserRepository followedUserRepository;
    @Autowired
    private IFollowedInterestRepository followedInterestRepository;


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.DELETE)
    public Board deleteBoard(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
        }
        else {
            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null)
                board = new Board(userName, boardName);
            else {
                //TODO esto deberia hacerse con querys mas optimos, y hacer updates en vez de saves,
                //ver: https://www.mkyong.com/mongodb/spring-data-mongodb-update-document/
                List<String> boardInterests = board.getFollowedInterests();
                List<String> boardUsers = board.getFollowedUsers();
                for (String followedInterestName : boardInterests) {
                    FollowedInterest interestItem = followedInterestRepository.findByInterestName(followedInterestName);
                    interestItem.decrementNumberOfUses(); //Si devolviera 0, podría borrarlo de la lista de intereses únicos
                    followedInterestRepository.save(interestItem);//Lo actualizo en la tabla de intereses únicos.
                }
                for (String followedUserName : boardUsers) {
                    FollowedUser userItem = followedUserRepository.findByUserName(followedUserName);
                    userItem.decrementNumberOfUses(); //Si devolviera 0, podría borrarlo de la lista de usuarios únicos
                    followedUserRepository.save(userItem);//Lo actualizo en la tabla de usuarios únicos.
                }
                boardRepository.deleteByUserNameAndBoardName(userName, boardName);
            }
            return board;
        }
        return new Board ("","");
    }
}

