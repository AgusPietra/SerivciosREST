package com.rb.module.interest.service;

import com.rb.module.interest.dao.IInterestRepository;
import com.rb.module.interest.entity.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
@EnableMongoRepositories(basePackageClasses = {IInterestRepository.class})
public class InterestService {

    @Autowired
    private IInterestRepository interestRepository;

    @Autowired
    public InterestService(IInterestRepository followedInterestRepository) {
        this.interestRepository = followedInterestRepository;
    }

    public Interest findByInterestName(String interestName){
        return interestRepository.findByInterestName(interestName);
    }
    public List<Interest> findAllInterests(){
        return interestRepository.findAll();
    }

    public void save (Interest interest){
        interestRepository.save(interest);
    }

    public void setNewInterests(List<String> interestsNames){
        for(String interestName: interestsNames){
            Interest existingInterest = findByInterestName(interestName);
            if(existingInterest == null){//Si no existe en la base de intereses, lo creo.
                Interest interest = new Interest(interestName);
                save(interest);
                System.out.println("Saved new interest named: " + interest.getInterestName() + " on interest table" );
            }
            else{//Si existe, actualizo la fecha de consulta, dado que un usuario lo acaba de agregar a su board.
                existingInterest.setAsked();
                save(existingInterest);
                System.out.println("Update existing interest named: " + existingInterest.getInterestName() + " on interest table" );
            }

        }
    }

    public long deleteInterestsByLastTimeAskedBefore(Calendar calendar){
        return interestRepository.deleteInterestsByLastTimeAskedBefore(calendar);
    }
//
    public long count(){
        return interestRepository.count();
    }
}
