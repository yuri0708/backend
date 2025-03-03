package com.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    @ManyToMany
    @JoinTable(
        name = "USERS_TASKS",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idTarefa"))
    private Set<TaskModel> tasks = new HashSet<>();

    public UUID getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(UUID idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public Set<TaskModel> getTasks(){
        return tasks;
    }

    public void setTasks(Set<TaskModel> tasks){
        this.tasks = tasks;
    }
}
