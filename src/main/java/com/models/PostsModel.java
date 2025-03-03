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
@Table(name = "POSTS")
public class PostsModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPost;
    private String descricao;
    private String dataPost;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private Set<CommentsModel> comments = new HashSet<>();

    public UUID getIdPost(){
        return idPost;
    }
    public void setIdPost(UUID idpost){
        this.idPost = idpost;
    }

    public String getdataPost(){
        return dataPost;
    }
    public void setdataPost(String dataPost){
        this.dataPost = dataPost;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Set<CommentsModel> getComentarios(){
        return comments;
    }

    public void setComentarios(Set<CommentsModel> comments){
        this.comments = comments;
    }
}
