package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final List<User> users = new ArrayList<>(){
        {
            add(User.builder().name("Roma").age(23).build());
            add(User.builder().name("Kate").age(18).build());
            add(User.builder().name("Vlad").age(12).build());
        }
    };


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/random")
    public User getRandomUser(){
      return userService.createUser();
    }

    @GetMapping()
    public List<User> getAll(){
      return users;
    }

    @GetMapping("/{name}")
    public User getUser(@PathVariable String name){
        for (User user : users) {
            if (user.getName().equals(name)) return user;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found");
    }


    @PostMapping
    public User updateUser(@RequestBody User user){

        for (User u : users) {
            if (user.getName().equals(u.getName())) {
                u.setAge(user.getAge());
                return u;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found");
    }

    @PutMapping
    public User createUser(@RequestBody User user) {

        for (User user1 : users) {
            if (user1.getName().equals(user.getName())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "user with this name is already exist");
            }
        }
        users.add(user);
        return user;
    }

    @DeleteMapping("/{name}")
    public User deleteUser(@PathVariable String name){

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                return users.remove(i);
            }
        }

        throw new ResponseStatusException(
                HttpStatus.I_AM_A_TEAPOT);
    }


}
