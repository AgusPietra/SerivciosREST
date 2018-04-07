package hello;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Id
    public String id;

    public String userName;
    public String boardName;
    public List<String> interests;
    public List<String> following;

    public Board() {}

    public Board(String userName, String boardName) {
        this.userName = userName;
        this.boardName = boardName;

        this.interests = new ArrayList<>();
        this.following = new ArrayList<>();

    }

    /*@Override
    public String toString() {
        return String.format(
                "User[id=%s, userName='%s', interest='%s']",
                id, userName, interest);
    }
*/
    public String getUserName() {
        return userName;
    }

    public String getBoardName() {
        return boardName;
    }

    public List<String> getInterests() {
        return interests;
    }
    public List<String> getFollowing() {
        return following;
    }
    public boolean setInterest(String interest) {
        //TODO chequear que no esté en la lista previamente
        interests.add( interest);
        return true;
    }
    public boolean setFollowing(String followed) {
        //TODO chequear que no esté en la lista previamente
        following.add( followed);
        return true;
    }


}
