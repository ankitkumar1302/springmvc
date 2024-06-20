package com.example.springmvc.controller;

import com.example.springmvc.User;
import com.example.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping(path = "/demo")
//public class MainController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/users")
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//    @GetMapping("/users")
//    public List<User> readUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> readUser(@PathVariable("id") Long id) {
//        Optional<User> userData = userRepository.findById(Math.toIntExact(id));
//
//        if (userData.isPresent()) {
//            return ResponseEntity.ok(userData.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
//        Optional<User> userData = userRepository.findById(Math.toIntExact(id));
//
//        if (userData.isPresent()) {
//            User _user = userData.get();
//            _user.setUsername(user.getUsername());
//            _user.setPassword(user.getPassword());
//            return ResponseEntity.ok(userRepository.save(_user));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
//        try {
//            userRepository.deleteById(Math.toIntExact(id));
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//}