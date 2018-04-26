package com.rb.module.board.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Id
    public String id;

    public String userName;
    public String boardName;
    public List<String> followedInterests;

    public Board() {}

    public Board(String userName, String boardName) {
        this.userName = userName;
        this.boardName = boardName;

        this.followedInterests = new ArrayList<>();

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

    public List<String> getFollowedInterests() {
        if(followedInterests == null) return new ArrayList<>();//Porque al principio esta lista no se inicializaba si no habia intereses
        return followedInterests;
    }
    public boolean setFollowedInterest(String followedInterest) {
        if(followedInterests.contains(followedInterest))
            return false;
        followedInterests.add(followedInterest);
        return true;
    }
}
