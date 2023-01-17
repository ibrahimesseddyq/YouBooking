package com.example.youbooking.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatusReservation statusReservation;
    @ManyToOne
    private Chamber chamber;
    @ManyToOne
    private Client client;


    public Reservation(LocalDate dateDebut, LocalDate dateFin, StatusReservation statusReservation, Chamber chamber, Client client) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statusReservation = statusReservation;
        this.chamber = chamber;
        this.client = client;
    }


}
