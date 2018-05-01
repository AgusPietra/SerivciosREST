package com.rb.module.board.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.BoardService;
import com.rb.module.common.response.codes.Code;
import com.rb.module.kafka.producers.IKafkaProducers;
import com.rb.module.user.entity.User;
import com.rb.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class DeleteBoardOfUserController {

    private IKafkaProducers kafkaProducers;
    @Autowired
    public DeleteBoardOfUserController(IKafkaProducers kafkaProducers) {
        this.kafkaProducers = kafkaProducers;
    }

    @RequestMapping(value = {"/users/{userName}/boards/{boardName}"}, method = RequestMethod.DELETE)
    public Code deleteBoard(@PathVariable(value="userName") String userName, @PathVariable(value="boardName") String boardName) {
        kafkaProducers.deleteBoard(new Board(userName, boardName));
        return new Code(0);
    }
}

