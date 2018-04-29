package com.rb.module.interest.rest;

import com.rb.module.interest.entity.Interest;
import com.rb.module.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class GetFollowedInterestContentsController {

    private InterestService interestService;
    @Autowired
    public GetFollowedInterestContentsController(InterestService interestService) {
        this.interestService = interestService;
    }

    @RequestMapping(value = {"/interests/{interest}"}, method = RequestMethod.GET)
    public List<String> followedInterest(@PathVariable(value="interest") String interestName) {

        System.out.println("accessing content of: " + interestName);
        Interest interestItem = this.interestService.findFirstByInterestName(interestName);
        if(interestItem == null) {
            interestItem = new Interest(interestName);//El interés fue borrado de la tabla, porque hace mucho que no
                                                      // se consultaba
            this.interestService.save(interestItem);//Entonces lo agrego
            System.out.println("Added iinterest: "+interestName);
            return new ArrayList<>();
        }
        interestItem.setAsked();//Aviso que se preguntó
        this.interestService.save(interestItem);//TODO, hacer úptimo, solo actualizar la indicación de que se consultó
        return interestItem.getContents();
    }
}
