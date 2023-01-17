package com.example.youbooking.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telephone;
    private String password;
    private Status status;
    private String photo;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Adresse adresse;


    public User(String nom, String email, String telephone, String password, Status status, Role role, Adresse adresse) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.status = status;
        this.role = role;
        this.adresse = adresse;
    }

}


