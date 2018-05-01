package com.rb.module.board.service;

import com.rb.module.board.entity.Board;

import java.util.List;

public interface IBoardService {

    Board findByUserNameAndBoardName(String userName, String boardName);

    List<Board> findByUserName(String userName);

    void deleteAllByUserNameAndBoardName(String userName, String boardName);

    void updateBoardOfUser(Board board, String boardOriginalName);

    void save (Board board);
}
