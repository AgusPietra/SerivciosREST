package com.rb.module.user.service;

import com.rb.module.user.dao.IUserRepository;
import com.rb.module.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableMongoRepositories(basePackageClasses = IUserRepository.class)
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUserName(userName);
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public void deleteByUserName(String userName){
        this.userRepository.deleteByUserName(userName);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

}
