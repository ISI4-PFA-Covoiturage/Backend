package com.supmti.covoiturage.repo;

import com.supmti.covoiturage.model.Internaute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InternauteRepo extends JpaRepository<Internaute , Long> {
    Optional<Internaute> findInternauteById(Long id);

    @Query("SELECT i FROM Internaute i WHERE i.verifie = ?1")
    Optional<List<Internaute>> findInternautesByVerifie(String verifie);

    @Query("SELECT COUNT(i),MONTH(i.date_creation) FROM Internaute i WHERE i.verifie = ?1 GROUP BY MONTH(i.date_creation) ORDER BY MONTH(i.date_creation)")
    Object[] countInternauteByDateCreation(String verifie);

    @Query("SELECT COUNT(i) FROM Internaute i WHERE i.verifie = 'Approuve'")
    int countInternauteTotal();

    Optional<Internaute> findByUsername(String username);

    @Query("SELECT i FROM Internaute i WHERE i.username = ?1")
    Optional<Internaute> findInternauteByUsername(String username);
}
