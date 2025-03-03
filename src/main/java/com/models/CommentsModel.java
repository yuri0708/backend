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
@Table(name = "COMMENTS")
public class CommentsModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idComentario;
    private String texto;
    private String hora;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private PostsModel posts;

    public UUID getIdComentario(){
        return idComentario;
    }

    public void setIdComentario(UUID id){
        this.idComentario = id;
    }

    public String getTexto(){
        return texto;
    }
    
    public void setTexto(String texto){
        this.texto = texto;
    }

    public String getHora(){
        return hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public PostsModel getPosts() {
        return posts;
    }

    public void setPosts(PostsModel posts){
        this.posts = posts;
    }
}
