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

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserRepository userRepository;

        @GetMapping
        public ResponseEntity<List<UserModel>> getAllUsers(){
            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
            Optional<UserModel> user = userRepository.findById(id);
            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        
        @PutMapping("{id}")
        public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto){
            Optional<UserModel> user = userRepository.findById(id);
            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
            var userModel = user.get();
            BeanUtils.copyProperties(userRecordDto, userModel);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");
        }

        @DeleteMapping("{id}")
        public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id){
            Optional<UserModel> user = userRepository.findById(id);
            if(user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
            userRepository.delete(user.get());
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
        }


}
