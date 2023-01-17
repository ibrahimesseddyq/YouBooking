package com.example.youbooking.controllers;

import com.example.youbooking.dto.ChamberDto;
import com.example.youbooking.dto.ReservationCheckDto;
import com.example.youbooking.entities.Chamber;
import com.example.youbooking.entities.Reservation;
import com.example.youbooking.entities.StatusChamber;
import com.example.youbooking.entities.StatusReservation;
import com.example.youbooking.services.IChamberService;
import com.example.youbooking.services.IReservationService;
import com.example.youbooking.services.dto.ResponseDTO;
import com.example.youbooking.utiles.DtoToEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chamber")
@CrossOrigin("http://localhost:4200")
public class ChamberController {

    @Autowired
    IChamberService chamberService;
    @Autowired
    IReservationService reservationService;

    @GetMapping
    public ResponseDTO getAll(){
        return chamberService.findAllChamber();
    }

    @GetMapping("/one/{id}")
    public ResponseDTO findOne(@PathVariable Long id){
        return chamberService.findOneChamber(id);
    }

    @PostMapping("/add")
    public ResponseDTO addChamber(@Valid @RequestBody ChamberDto chamberDto){
        Chamber chamber = DtoToEntity.chamberDtoToChamber(chamberDto);
        return chamberService.addChamber(chamber);
    }

    @PutMapping("/update/{id}/{idHotel}")
    public ResponseDTO updateChamber(@Valid @RequestBody ChamberDto chamberDto,@PathVariable Long id,@PathVariable Long idHotel){
        chamberDto.setId(id);
        Chamber chamber = DtoToEntity.chamberDtoToChamber(chamberDto);
        return chamberService.updateChamber(chamber,idHotel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteChamber(@PathVariable Long id){
        return chamberService.deleteChamber(id);
    }

    @PostMapping("/reserver")
    public ResponseDTO resrever(@RequestBody Reservation reservation){
        reservation.setStatusReservation(StatusReservation.Encours);

        return reservationService.addReservation(reservation);
    }

@PostMapping("/chamberDespo")
    public List<Chamber> findChambersDespo(@RequestBody Reservation reservation,@RequestParam("ville") String ville){
        return chamberService.findChambersBySatatus(reservation,ville);
}
@GetMapping("/chamberbyhotel/{id}")
    public List<Chamber> findChambersByHotel(@PathVariable Long id){
        return chamberService.findActiveChambersByHotel(id);
}
@PostMapping("/checkReserved")
    public Boolean checkisReserved(@RequestBody ReservationCheckDto reservationCheckDto){
        return reservationService.checkRoomIsReserved(reservationCheckDto.startDate,reservationCheckDto.endDate,reservationCheckDto.chamber_id);

}

}
