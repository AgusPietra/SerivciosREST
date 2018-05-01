package com.rb.module.kafka.producers;

import com.rb.module.board.entity.Board;

public interface IKafkaProducers {

    void sendPetition(String test);
    void deleteBoard(Board board);
    void setNewBoard(Board board);
    void updateBoard(Board board, String originalName);
    void getInterestAssociatedActions(String interestName);

}
