package hello;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    public String id;

    public String userName;
    public String interest;

    public User() {}

    public User(String userName, String interest) {
        this.userName = userName;
        this.interest = interest;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, userName='%s', interest='%s']",
                id, userName, interest);
    }

    public String getUserName() {
        return userName;
    }

    public String getInterest() {
        return interest;
    }
}

