package com.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALBUMS")
public class AlbumsModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAlbum;
    private String nome;
    private String descricao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private Set<PhotosModel> photos = new HashSet<>();

    public UUID getIdAlbum(){
        return idAlbum;
    }
    public void setIdAlbum(UUID idalbum){
        this.idAlbum = idalbum;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Set<PhotosModel> getFotos(){
        return photos;
    }

    public void setFotos(Set<PhotosModel> photos){
        this.photos = photos;
    }
    
}
