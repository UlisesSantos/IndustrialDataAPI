package com.IndustrialDataAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name= "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "emailUser", nullable = false, unique = true)
    private String email;

    @Column(name = "passwordUser", nullable = false)
    private String password;

    @Column(name = "nameUser", nullable = false)
    private String name;

    @Column(name = "lastnameUser", nullable = false)
    private String lastname;

    public Users(Long userId, String email, String password, String name, String lastname){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }

    public Users(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
}
