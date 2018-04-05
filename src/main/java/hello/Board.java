package hello;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Board {
    @Id
    public String id;

    public String userName;
    public String boardName;
    public List<String> interests;
    public List<String> following;

    public Board() {}

    public Board(String userName, String boardName, List<String> interests,  List<String> following) {
        this.userName = userName;
        this.boardName = boardName;
        this.interests = interests;
        this.following = following;

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

    public String boardName() {
        return boardName;
    }

    public List<String> getInterests() {
        return interests;
    }
    public List<String> getFollowing() {
        return following;
    }
}
