package com.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtos.LoginRequestDto;
import com.dtos.ResponseDto;
import com.dtos.UserRecordDto;
import com.models.UserModel;
import com.repositories.UserRepository;
import com.security.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserRepository repository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body){
        UserModel user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));    
        if(passwordEncoder.matches(body.senha(), user.getSenha())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDto(user.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRecordDto body){
        Optional<UserModel> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()){
            UserModel newUser = new UserModel(); 
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            newUser.setTelefone(body.telefone());
            repository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getNome(), token));
            
            
        }
       
        return ResponseEntity.badRequest().build();
    }
}
