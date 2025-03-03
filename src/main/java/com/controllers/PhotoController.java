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

import com.dtos.PhotoRecordDto;
import com.models.PhotosModel;
import com.repositories.PhotoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @PostMapping
    public ResponseEntity<PhotosModel> savePhoto(@RequestBody @Valid PhotoRecordDto photoRecordDto) {
        var photoModel = new PhotosModel();
        BeanUtils.copyProperties(photoRecordDto, photoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(photoRepository.save(photoModel));
    }

    @GetMapping
    public ResponseEntity<List<PhotosModel>> getAllPhotos(){
        return ResponseEntity.status(HttpStatus.OK).body(photoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePhoto(@PathVariable(value="id") UUID id){
        Optional<PhotosModel> photos = photoRepository.findById(id);
        if(photos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(photos.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePhoto(@PathVariable(value="id") UUID id, @RequestBody @Valid PhotoRecordDto photoRecordDto){
        Optional<PhotosModel> photos = photoRepository.findById(id);
        if(photos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada!");
        }
        var photoModel = photos.get();
        BeanUtils.copyProperties(photoRecordDto, photoModel);
        return ResponseEntity.status(HttpStatus.OK).body(photoRepository.save(photoModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePhoto(@PathVariable(value="id") UUID id){
        Optional<PhotosModel> photos = photoRepository.findById(id);
        if(photos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada!");
        }
        photoRepository.delete(photos.get());
        return ResponseEntity.status(HttpStatus.OK).body("Foto deletada com sucesso!");
    }
}
