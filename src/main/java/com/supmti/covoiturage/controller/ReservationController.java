package com.supmti.covoiturage.controller;

import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.model.Reservation;
import com.supmti.covoiturage.service.AnnonceService;
import com.supmti.covoiturage.service.InternauteService;
import com.supmti.covoiturage.service.ReservationService;
import com.supmti.covoiturage.service.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final AnnonceService annonceService;
    private final InternauteService internauteService;
    private final VehiculeService vehiculeService;

    public ReservationController(ReservationService reservationService, AnnonceService annonceService, InternauteService internauteService, VehiculeService vehiculeService)
    {
        this.reservationService = reservationService;
        this.annonceService = annonceService;
        this.internauteService = internauteService;
        this.vehiculeService = vehiculeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Map<String, String> donnee){
        //   System.out.println(donnee);
        Reservation FirstReservation = new Reservation();
        FirstReservation.setAnnonce(annonceService.findAnnonceById(Long.valueOf(donnee.get("annonce"))));
        FirstReservation.setInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        FirstReservation.setRemarque(donnee.get("remarque"));
        FirstReservation.setStatut("En Attend");

        Reservation newReservation = reservationService.addReservation(FirstReservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @GetMapping("/findReservationsByAnnonce/{annonce}")
    public ResponseEntity<List<Reservation>> getReservationByAnnounce(@PathVariable("annonce") Long id){
        List<Reservation> reservations = reservationService.findReservationsByAnnonce(annonceService.findAnnonceById(id));
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/updateStatut/{statut}/{id}")
    public ResponseEntity<Reservation> updateReservationStatut(@PathVariable("statut") String statut, @PathVariable("id") Long id){
        Reservation sameReservation = new Reservation();
        sameReservation = reservationService.findReservationsById(id);
        sameReservation.setStatut(statut);
        reservationService.updateReservation(sameReservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/countExistByInternauteAndAnnonce/{internaute}/{annonce}")
    public ResponseEntity<Integer> getCountExistReservationByInternauteAndAnnonce(@PathVariable("internaute") Long idInternaute, @PathVariable("annonce") Long idAnnonce){
        int l = reservationService.countExistReservationByInternauteAndAnnonce(internauteService.findInternauteById(idInternaute),annonceService.findAnnonceById((idAnnonce)));
        return new ResponseEntity<>(l, HttpStatus.OK);
    }


    @GetMapping("/findReservationsByInternauteAndAnnonce/{internaute}/{annonce}")
    public ResponseEntity<Reservation> getReservationByInternauteAndAnnounce(@PathVariable("internaute") Long idInternaute, @PathVariable("annonce") Long idAnnonce){
        Reservation reservation = reservationService.findReservationsByInternauteAndAnnonce(internauteService.findInternauteById(idInternaute),annonceService.findAnnonceById(idAnnonce));
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/findReservationsByInternaute/{internaute}")
    public ResponseEntity<List<Reservation>> getReservationByInternaute(@PathVariable("internaute") Long id){
        List<Reservation> reservations = reservationService.findReservationsByInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


    //Dashboard

    //number Navbar
    @GetMapping("/dashboard/countReservationTotal")
    public int countReservationTotal() {
        int I = reservationService.countReservationTotal();
        return I;
    }

}
