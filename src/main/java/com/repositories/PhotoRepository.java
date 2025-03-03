package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.PhotosModel;

@Repository
public interface PhotoRepository extends JpaRepository<PhotosModel, UUID>{

}
