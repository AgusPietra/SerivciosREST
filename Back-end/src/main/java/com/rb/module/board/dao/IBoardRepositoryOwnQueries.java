package com.rb.module.board.dao;

import com.rb.module.board.entity.Board;

public interface IBoardRepositoryOwnQueries {
    void updateBoardOfUser(Board board, String boardOriginalName);
}
