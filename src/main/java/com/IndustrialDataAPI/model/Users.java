package com.IndustrialDataAPI.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name= "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "email_user", nullable = false, unique = true)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    @Column(name = "name_user", nullable = false)
    private String name;

    @Column(name = "lastname_user", nullable = false)
    private String lastname;

    @Column(name = "role_user", nullable = false)
    private String role;

    @Column(name = "created_at_user", nullable = false)
    private LocalDateTime createdAt;

    public Users(Long user_id, String email, String password, String name, String lastname, String role, LocalDateTime createdAt){
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Users(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
