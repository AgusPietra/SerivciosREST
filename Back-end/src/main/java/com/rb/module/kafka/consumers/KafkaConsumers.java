package com.rb.module.kafka.consumers;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import com.rb.module.kafka.producers.IKafkaProducers;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

@Component
public class KafkaConsumers {

    private UserService userService;
    private BoardService boardService;

    @Autowired
    public KafkaConsumers(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }



    @KafkaListener(topics = "TestTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("Received from TestTopic");
        System.out.println(cr.value());
    }

    @KafkaListener(topics = "DeleteBoardTopic")
    public void deleteBoardConsumer(ConsumerRecord<?, ?> cr) throws Exception {

        Board board = (Board)SerializationUtils.deserialize(Base64.getDecoder().decode(cr.value().toString()));
        boardService.deleteAllByUserNameAndBoardName(board.userName, board.boardName);
        System.out.println("Deleted board: " + board.boardName + " from user: " + board.userName);
    }


}
