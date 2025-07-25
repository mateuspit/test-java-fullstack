package com.minsait.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.user.dtos.UserDTO;
import com.minsait.user.models.UserModel;
import com.minsait.user.services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO body) {
        Optional<UserModel> user = userService.save(body);

        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registred.");
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
    }    

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable Long id) {
        Optional<UserModel> user = userService.findById(id);

        if (!user.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO body) {
        Optional<UserModel> user = userService.findById(id);

        if (!user.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(body, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {   
         Optional<UserModel> user = userService.findById(id);

        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User " + id + " deleted");
    }
}
