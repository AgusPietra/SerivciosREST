package com.rb.module.board.service;

import com.rb.module.board.dao.IBoardRepository;
import com.rb.module.board.dao.IBoardRepositoryOwnQueries;
import com.rb.module.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableMongoRepositories(basePackageClasses = IBoardRepository.class)
public class BoardService implements IBoardService{
    @Autowired
    private IBoardRepository boardRepository;

    @Autowired
    private IBoardRepositoryOwnQueries boardRepositoryOwnQueries;

    @Autowired
    public BoardService(IBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board findByUserNameAndBoardName(String userName, String boardName) {
        return this.boardRepository.findByUserNameAndBoardName(userName, boardName);
    }

    public List<Board> findByUserName(String userName){
        return this.boardRepository.findByUserName(userName);
    }

    public void deleteAllByUserNameAndBoardName(String userName, String boardName){
        this.boardRepository.deleteAllByUserNameAndBoardName(userName, boardName);
    }

    public void updateBoardOfUser(Board board, String boardOriginalName){
        this.boardRepositoryOwnQueries.updateBoardOfUser(board, boardOriginalName);
    }

    public void save (Board board) {
        this.boardRepository.save(board);
    }
}
