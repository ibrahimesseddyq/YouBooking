package com.example.youbooking.dto;

import com.example.youbooking.entities.StatusReservation;

import java.time.LocalDate;

public class ReservationCheckDto {
    public LocalDate startDate;
    public LocalDate endDate;
    public Long chamber_id;
}
