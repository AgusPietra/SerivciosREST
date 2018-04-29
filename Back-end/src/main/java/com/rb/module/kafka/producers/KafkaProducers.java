package com.rb.module.kafka.producers;

import com.rb.module.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

@Component
public class KafkaProducers implements IKafkaProducers {
    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendPetition(String test){
//        System.out.println(test);
        this.template.send("TestTopic", test);
        System.out.println("Sended to TestTopic");
    }

    public void deleteBoard(Board board){

        String str = Base64.getEncoder().encodeToString(SerializationUtils.serialize(board));
        this.template.send("DeleteBoardTopic", str);
    }
}
