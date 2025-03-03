package com.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtos.CommentRecordDto;
import com.models.CommentsModel;
import com.repositories.CommentRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<CommentsModel> saveComment(@RequestBody @Valid CommentRecordDto commentRecordDto) {
        var commentModel = new CommentsModel();
        BeanUtils.copyProperties(commentRecordDto, commentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentRepository.save(commentModel));
    }

    @GetMapping
    public ResponseEntity<List<CommentsModel>> getAllComments(){
        return ResponseEntity.status(HttpStatus.OK).body(commentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneComment(@PathVariable(value="id") UUID id){
        Optional<CommentsModel> comments = commentRepository.findById(id);
        if(comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comments.get());
    }

    
    @PutMapping("{id}")
    public ResponseEntity<Object> updateComment(@PathVariable(value="id") UUID id, @RequestBody @Valid CommentRecordDto commentRecordDto){
        Optional<CommentsModel> comments = commentRepository.findById(id);
        if(comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado!");
        }
        var comentarioModel = comments.get();
        BeanUtils.copyProperties(commentRecordDto, comentarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(commentRepository.save(comentarioModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable(value="id") UUID id){
        Optional<CommentsModel> comments = commentRepository.findById(id);
        if(comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado!");
        }
        commentRepository.delete(comments.get());
        return ResponseEntity.status(HttpStatus.OK).body("Comentário deletado com sucesso!");
    }
}
