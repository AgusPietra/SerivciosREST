package com.rb.module.kafka.producers;

import com.rb.module.board.entity.Board;

public interface IKafkaProducers {

    void sendPetition(String test);
    void deleteBoard(Board board);

}
