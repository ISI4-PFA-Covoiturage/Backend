package com.supmti.covoiturage.repo;

import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.annonce = ?1")
    Optional<List<Reservation>> findReservationsByAnnonce(Annonce annonce);

    @Query("UPDATE Reservation r SET r.statut =?1 WHERE r.id = ?2")
    Optional<Reservation> updateReservationsByIdAndStatut(String statut, Long id);

    Optional<Reservation> findReservationsById(Long id);


    @Query("SELECT count(r) FROM Reservation r WHERE r.internaute = ?1 AND r.annonce = ?2")
    int countExistReservationByInternauteAndAnnonce(Internaute internaute, Annonce annonce);

    @Query("SELECT r FROM Reservation r WHERE r.internaute = ?1 AND r.annonce = ?2")
    Optional<Reservation> findReservationsByInternauteAndAnnonce(Internaute internaute, Annonce annonce);

    @Query("SELECT r FROM Reservation r WHERE r.internaute = ?1")
    Optional<List<Reservation>> findReservationsByInternaute(Internaute internaute);

    @Query("SELECT COUNT(r) FROM Reservation r")
    int countReservationTotal();
}
