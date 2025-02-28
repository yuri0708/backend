package com.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.dtos.UserRecordDto;
import com.models.UserModel;
import com.repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

        @Autowired
        UserRepository userRepository;

        @PostMapping("/users")
        public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
            var userModel = new UserModel();
            BeanUtils.copyProperties(userRecordDto, userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
        }

        @GetMapping("/users")
        public ResponseEntity<List<UserModel>> getAllUsers(){
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
        }
        
        @GetMapping("/users/{id}")
        public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
            Optional<UserModel> user1 = userRepository.findById(id);
            if(user1.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(user1.get());
        }
        
}
