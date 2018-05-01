package com.rb.module.interest.rest;

import com.rb.module.board.entity.Board;
import com.rb.module.interest.entity.Interest;
import com.rb.module.interest.service.IInterestService;
import com.rb.module.kafka.producers.IKafkaProducers;
import com.rb.module.kafka.producers.KafkaProducers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class GetFollowedInterestContentsController {

    private IInterestService interestService;
    private IKafkaProducers kafkaProducers;
    @Autowired
    public GetFollowedInterestContentsController(IKafkaProducers kafkaProducers, IInterestService interestService) {
        this.interestService = interestService;
        this.kafkaProducers = kafkaProducers;
    }

    @RequestMapping(value = {"/interests/{interest}"}, method = RequestMethod.GET)
    public List<String> followedInterest(@PathVariable(value="interest") String interestName,
                                         @RequestParam("count") int countAsked) {

        Interest interestItem = this.interestService.findFirstByInterestName(interestName);
        kafkaProducers.getInterestAssociatedActions(interestName);
        if(interestItem == null) {
            return new ArrayList<>();
        }
        return interestItem.getContents(countAsked);
    }
}
