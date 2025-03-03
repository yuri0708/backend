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

import com.dtos.PostRecordDto;
import com.models.PostsModel;
import com.repositories.PostRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping
    public ResponseEntity<PostsModel> savePost(@RequestBody @Valid PostRecordDto postRecordDto) {
        var postModel = new PostsModel();
        BeanUtils.copyProperties(postRecordDto, postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(postModel));
    }

    @GetMapping
    public ResponseEntity<List<PostsModel>> getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePost(@PathVariable(value="id") UUID id){
        Optional<PostsModel> posts = postRepository.findById(id);
        if(posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(posts.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value="id") UUID id, @RequestBody @Valid PostRecordDto postRecordDto){
        Optional<PostsModel> posts = postRepository.findById(id);
        if(posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        var postModel = posts.get();
        BeanUtils.copyProperties(postRecordDto, postModel);
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(postModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value="id") UUID id){
        Optional<PostsModel> posts = postRepository.findById(id);
        if(posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        postRepository.delete(posts.get());
        return ResponseEntity.status(HttpStatus.OK).body("Post deletado com sucesso!");
    }
}
