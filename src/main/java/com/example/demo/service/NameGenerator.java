package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NameGenerator {

    private List<String> names = List.of("Roma", "Angray", "Fill", "Stepan");

    public String generateName(){
        return names.get(getRandomNumber(0, 3));
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
