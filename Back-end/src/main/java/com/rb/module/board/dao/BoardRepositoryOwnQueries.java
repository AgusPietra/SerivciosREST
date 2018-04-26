package com.rb.module.board.dao;

import com.rb.module.board.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardRepositoryOwnQueries implements  IBoardRepositoryOwnQueries{
    public void updateBoardOfUser(Board board){
        System.out.println("UPDATING BOARD: " + board.getBoardName() + " from user: " + board.getUserName());
    }
}
