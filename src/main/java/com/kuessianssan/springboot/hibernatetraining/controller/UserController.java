package com.kuessianssan.springboot.hibernatetraining.controller;


import com.kuessianssan.springboot.hibernatetraining.entity.User;
import com.kuessianssan.springboot.hibernatetraining.exception.ResourceNotFoundException;
import com.kuessianssan.springboot.hibernatetraining.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;


    //@RequestMapping("/api/users")
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

//    @RequestMapping("api/users/{userId}")
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :" + userId));
    }


//    @RequestMapping(value = "/api/users", method = RequestMethod.POST) //OR @PostMapping
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }


//    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.DELETE)

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable long userId){
        User existing = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :" + userId));

        this.userRepository.delete(existing);

        return ResponseEntity.ok().build();
    }


//    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.PUT)
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User user){
        User existing = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :" + userId));

        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());

        this.userRepository.save(existing);

        return existing;
    }


}
