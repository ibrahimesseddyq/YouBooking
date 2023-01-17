package com.example.youbooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Chamber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private Double prix;
    private Integer nomberLits;
    private StatusChamber statusChamber;
    private String photo;
    @ManyToOne
    @JsonIgnore
    private Hotel hotel;
    @OneToMany(mappedBy = "chamber" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservationList;


    public Chamber(Integer numero, Double prix, Integer nomberLits, StatusChamber statusChamber, Hotel hotel) {
        this.numero = numero;
        this.prix = prix;
        this.nomberLits = nomberLits;
        this.statusChamber = statusChamber;
        this.hotel = hotel;
    }
    public String toString(){
        return "";
    }
}
