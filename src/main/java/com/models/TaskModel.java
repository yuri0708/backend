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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TASKS")
public class TaskModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTarefa;
    private String descricao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
    private Set<UserModel> users = new HashSet<>();

    public UUID getIdTarefa(){
        return idTarefa;
    }
    public void setIdTarefa(UUID idTarefa){
        this.idTarefa = idTarefa;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Set<UserModel> getUsers(){
        return users;
    }

    public void setUsers(Set<UserModel> users){
        this.users = users;
    }
}
