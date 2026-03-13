package com.IndustrialDataAPI.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "Roles")
public class Roles {

    @Id
    private Long role_id;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    public Roles(){}

    public Roles(Long id){
        this.role_id = id;
    }

    public Roles(Long id, String role) {
        this.role_id = id;
        this.role = role;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
