package com.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtos.TaskRecordDto;
import com.models.TaskModel;
import com.repositories.TaskRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskModel));
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value="idTarefa") UUID id){
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(task.get());
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(value="idTarefa") UUID id, @RequestBody @Valid TaskRecordDto taskRecordDto){
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        var taskModel = task.get();
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value="idTarefa") UUID id){
        Optional<TaskModel> task = taskRepository.findById(id);
        if(task.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        taskRepository.delete(task.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso!");
    }
    
}
