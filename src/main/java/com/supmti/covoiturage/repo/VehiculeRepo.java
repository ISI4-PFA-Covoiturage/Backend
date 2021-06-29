package com.supmti.covoiturage.repo;

import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface VehiculeRepo extends JpaRepository<Vehicule, Long> {
    @Query("SELECT v FROM Vehicule v WHERE v.id_vehicule = ?1")
    Optional<Vehicule> findVehiculeById(Long id);

    Optional<Vehicule> findVehiculeByInternaute(Internaute internaute);

    @Query("SELECT count(v) FROM Vehicule v WHERE v.internaute = ?1")
    int countVehiculeByInternaute(Internaute internaute);

    @Query("SELECT count(v) FROM Vehicule v")
    int countVehiculeTotal();
}
