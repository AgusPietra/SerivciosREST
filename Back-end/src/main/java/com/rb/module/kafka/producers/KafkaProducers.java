package com.rb.module.kafka.producers;

import com.rb.module.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class KafkaProducers implements IKafkaProducers {
    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendPetition(String test){
        this.template.send("TestTopic", test);
        System.out.println("Sended to TestTopic");
    }

    public void deleteBoard(Board board){

        String str = Base64.getEncoder().encodeToString(SerializationUtils.serialize(board));
        this.template.send("DeleteBoardTopic", str);
    }

    public void setNewBoard(Board board){
        String str = Base64.getEncoder().encodeToString(SerializationUtils.serialize(board));
        this.template.send("SetNewBoardTopic", str);
    }

    public void updateBoard(Board board, String originalName) {
        Board originalBoard = new Board(board.userName, originalName);
        List<Board> boards = new ArrayList<Board>();
        boards.add(board);
        boards.add(originalBoard);
        String str = Base64.getEncoder().encodeToString(SerializationUtils.serialize(boards));
        this.template.send("UpdateBoardTopic", str);
    }
    public void getInterestAssociatedActions (String interestName){
        this.template.send("getInterestAssociatedActionsTopic", interestName);
    }
}
