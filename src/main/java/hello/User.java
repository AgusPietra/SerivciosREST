package hello;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    public String id;

    public String userName;

    public User() {}

    public User(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, userName='%s']",
                id, userName);
    }

    public String getUserName() {
        return userName;
    }
}

