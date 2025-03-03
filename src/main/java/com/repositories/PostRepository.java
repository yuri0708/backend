package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.PostsModel;

@Repository
public interface PostRepository extends JpaRepository<PostsModel, UUID>{

}
