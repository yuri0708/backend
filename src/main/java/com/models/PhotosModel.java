package com.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PHOTOS")
public class PhotosModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFoto;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idAlbum")
    private AlbumsModel album;

    public UUID getIdFoto(){
        return idFoto;
    }
    public void setIdFoto(UUID idfoto){
        this.idFoto = idfoto;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public AlbumsModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumsModel album){
        this.album = album;
    }
}
