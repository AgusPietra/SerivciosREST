package com.rb.module.board.dao;

import java.util.List;

import com.rb.module.board.entity.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBoardRepository extends MongoRepository<Board, String> {

    Board findByUserNameAndBoardName(String userName, String boardName);
    List<Board> findByUserName(String userName);
    void deleteAllByUserNameAndBoardName(String userName, String boardName);
}
