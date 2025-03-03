package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID>{

}
