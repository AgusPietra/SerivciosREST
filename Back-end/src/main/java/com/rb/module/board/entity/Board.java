package com.rb.module.board.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Id
    public String id;

    public String userName;
    public String boardName;
    public List<String> interests;

    public Board() {}

    public Board(String userName, String boardName) {
        this.userName = userName;
        this.boardName = boardName;

        this.interests = new ArrayList<>();

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
        if(interests == null) return new ArrayList<>();//Porque al principio esta lista no se inicializaba si no habia intereses
        return interests;
    }
    public boolean setInterest(String interest) {
        if(this.interests.contains(interest))
            return false;
        this.interests.add(interest);
        return true;
    }

    public boolean deleteInterests() {
        this.interests.clear();
        return true;
    }

    public boolean setInterests(List<String> interests) {
        this.interests = new ArrayList<String>(interests);
        return true;
    }
}
