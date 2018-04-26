package com.rb.module.board.dao;

import com.rb.module.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.System.in;

@Component
public class BoardRepositoryOwnQueries implements  IBoardRepositoryOwnQueries{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired MongoOperations mongoOperations;

    public void updateBoardOfUser(Board board){
        System.out.println("UPDATING BOARD: " + board.getBoardName() + " from user: " + board.getUserName());
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("boardName").is(board.getBoardName()),
                Criteria.where("userName").is(board.getUserName()));
        query.addCriteria(criteria);
        List<Board> boardsF = mongoTemplate.find(query, Board.class);

        for (Board boardF: boardsF){
            System.out.println("Board name found: " + boardF.getBoardName());
            boardF.setInterests(board.interests);
            mongoOperations.save(boardF);
            for (String interest: boardF.interests){
                System.out.println("Interest of board: " + interest);
            }
        }


//        Query query = new Query();
//        int numOfRecord = 10;
//        if (numOfRecord > 0)
//            query.limit(numOfRecord);
//
//        query.with(new Sort(Sort.Direction.DESC, "count"));
//        query.fields().include("_id");
//        query.fields().include("domain");
//        query.fields().include("count");
//
//
//        return find(query, Hosting.class);
    }
}
