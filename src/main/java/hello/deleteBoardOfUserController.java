package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class deleteBoardOfUserController {

    @Autowired
    private IBoardRepository boardRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IFollowedInterestRepository followedInterestRepository;


    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.DELETE)
    public Board board(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {
        System.out.println("A");
        User userExists = userRepository.findByUserName(userName);
        if(userExists == null) {//TODO manegar situación de que el usuario no exista, aunque no debiera poder pasar
            System.out.println("B");
        }
        else {
            System.out.println("C");
            Board board = boardRepository.findByUserNameAndBoardName(userName, boardName);

            if (board == null)
                board = new Board(userName, boardName);
            else {
                System.out.println("D");
                //TODO esto deberia hacerse con querys mas optimos, y hacer updates en vez de saves,
                //ver: https://www.mkyong.com/mongodb/spring-data-mongodb-update-document/
                List<String> boardInterests = board.getFollowedInterests();
                List<String> boardUsers = board.getFollowedUsers();
                for (String followedInterestName : boardInterests) {
                    System.out.println(followedInterestName);
                    FollowedInterest interestItem = followedInterestRepository.findByInterestName(followedInterestName);
                    interestItem.decrementNumberOfUses(); //Si devolviera 0, podría borrarlo de la lista de intereses únicos
                    followedInterestRepository.save(interestItem);//Lo actualizo en la tabla de intereses únicos.
                }
                for (String followedUserName : boardUsers) {
                    System.out.println(followedUserName);
                    FollowedInterest userItem = followedInterestRepository.findByInterestName(followedUserName);
                    userItem.decrementNumberOfUses(); //Si devolviera 0, podría borrarlo de la lista de usuarios únicos
                    followedInterestRepository.save(userItem);//Lo actualizo en la tabla de usuarios únicos.
                }
                boardRepository.deleteByUserNameAndBoardName(userName, boardName);
            }
            System.out.println("E");
            return board;
        }
        System.out.println("F");
        return new Board ("","");
    }
}
