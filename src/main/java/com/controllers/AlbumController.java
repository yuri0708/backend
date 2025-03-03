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

import com.dtos.AlbumRecordDto;
import com.models.AlbumsModel;
import com.repositories.AlbumRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @PostMapping
    public ResponseEntity<AlbumsModel> saveAlbum(@RequestBody @Valid AlbumRecordDto albumRecordDto) {
        var albumModel = new AlbumsModel();
        BeanUtils.copyProperties(albumRecordDto, albumModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(albumRepository.save(albumModel));
    }

    @GetMapping
    public ResponseEntity<List<AlbumsModel>> getAllAlbums(){
        return ResponseEntity.status(HttpStatus.OK).body(albumRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAlbum(@PathVariable(value="id") UUID id){
        Optional<AlbumsModel> album = albumRepository.findById(id);
        if(album.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Álbum não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(album.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateAlbum(@PathVariable(value="id") UUID id, @RequestBody @Valid AlbumRecordDto albumRecordDto){
        Optional<AlbumsModel> album = albumRepository.findById(id);
        if(album.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Álbum não encontrado!");
        }
        var albumModel = album.get();
        BeanUtils.copyProperties(albumRecordDto, albumModel);
        return ResponseEntity.status(HttpStatus.OK).body(albumRepository.save(albumModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAlbum(@PathVariable(value="id") UUID id){
        Optional<AlbumsModel> album = albumRepository.findById(id);
        if(album.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Álbum não encontrado!");
        }
        albumRepository.delete(album.get());
        return ResponseEntity.status(HttpStatus.OK).body("Álbum deletado com sucesso!");
    }
}
