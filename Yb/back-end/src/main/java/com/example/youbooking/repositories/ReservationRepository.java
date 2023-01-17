package com.example.youbooking.repositories;

import com.example.youbooking.entities.Chamber;
import com.example.youbooking.entities.Reservation;
import com.example.youbooking.entities.StatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    public List<Reservation> findReservationByClientAndStatusReservation(Long idClient, StatusReservation status);

    public List<Reservation> findReservationByStatusReservation(StatusReservation status);

    public List<Reservation> findReservationByChamberAndStatusReservation(Long idChmaber,StatusReservation status);

    public Reservation findReservationByChamberAndDateDebutAndAndDateFinAndStatusReservation(
            Chamber chamber
            , LocalDate dateDebut
            ,LocalDate dateFin
            ,StatusReservation status);
    @Query(value = "SELECT *  FROM Reservation r INNER JOIN Chamber c" +
            " ON r.chamber_id = c.id WHERE (((r.date_debut >= :startDate " +
            "and r.date_debut <= :endDate) or (r.date_fin >=:startDate and " +
            "r.date_fin <= :endDate)) or ((r.date_debut<= :startDate and" +
            " r.date_fin >= :endDate  ) and (r.date_debut <=:endDate and" +
            " r.date_fin >= :endDate)) or ((r.date_debut>= :startDate and " +
            "r.date_fin >= :startDate ) and (r.date_fin<=:endDate))) and c.id=:chamber_id and " +
            "r.status_reservation=1",nativeQuery = true)
    public List<Reservation> findReservationBetweenDates(LocalDate startDate,LocalDate endDate,Long chamber_id);
}

