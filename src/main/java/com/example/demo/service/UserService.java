package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {


    private final NameGenerator nameGenerator;

    @Autowired
    public UserService(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }


    public User createUser(){
        return User
                .builder()
                .name(nameGenerator.generateName())
                .age(nameGenerator.getRandomNumber(10, 78))
                .build();
    }
}
