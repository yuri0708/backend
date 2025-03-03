package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.CommentsModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentsModel, UUID>{

}
