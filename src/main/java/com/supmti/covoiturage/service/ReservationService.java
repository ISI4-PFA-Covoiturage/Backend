package com.supmti.covoiturage.service;

import com.supmti.covoiturage.exception.ReservationNotFoundException;
import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.model.Reservation;
import com.supmti.covoiturage.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    private final ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo){this.reservationRepo = reservationRepo;}

    public Reservation addReservation(Reservation reservation)
    {
        return reservationRepo.save(reservation);
    }

    public List<Reservation> findReservationsByAnnonce(Annonce annonce){return reservationRepo.findReservationsByAnnonce(annonce)
            .orElseThrow(()-> new ReservationNotFoundException("Reservation by Annonce"+annonce+"was not found"));
    }

    public Reservation findReservationsById(Long id){return reservationRepo.findReservationsById(id)
            .orElseThrow(()-> new ReservationNotFoundException("Reservation by id"+id+"was not found"));
    }


     public Reservation updateReservation(Reservation reservation){return  reservationRepo.save(reservation);}


    public Reservation updateReservationsByIdAndStatut(String statut,Long id){return reservationRepo.updateReservationsByIdAndStatut(statut,id)
            .orElseThrow(()-> new ReservationNotFoundException("Reservation by id"+id+"was not found"));
    }


        public int countExistReservationByInternauteAndAnnonce(Internaute internaute, Annonce annonce){
        return reservationRepo.countExistReservationByInternauteAndAnnonce(internaute, annonce);
    }

    public Reservation findReservationsByInternauteAndAnnonce(Internaute internaute, Annonce annonce){
        return reservationRepo.findReservationsByInternauteAndAnnonce(internaute,annonce).orElse(null);
    }

    public List<Reservation> findReservationsByInternaute(Internaute internaute){
        return reservationRepo.findReservationsByInternaute(internaute).orElse(null);
    }

    //Dashboard

    //number navbar
    public int  countReservationTotal() { return reservationRepo.countReservationTotal(); }

}
