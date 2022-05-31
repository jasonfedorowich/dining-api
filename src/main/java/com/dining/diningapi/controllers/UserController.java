package com.dining.diningapi.controllers;

import com.dining.diningapi.model.DiningUser;
import com.dining.diningapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;



    @GetMapping
    public List<DiningUser> getAllUsers(){
        List<DiningUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @PostMapping
    public DiningUser createUser(@RequestBody DiningUser user) {
        try{
            return userRepository.save(user);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }

    @GetMapping("/{name}")
    public DiningUser getUser(@PathVariable String name){
        Optional<DiningUser> opt = userRepository.findByName(name);
        if(opt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return opt.get();
    }





}
