package com.supmti.covoiturage.repo;

import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.model.Internaute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AnnonceRepo extends JpaRepository<Annonce , Long> {
    void deleteAnnonceById(Long id);

    Optional<Annonce> findAnnonceById(Long id);


    Optional<List<Annonce>> findAnnoncesByInternaute(Internaute internaute);

    @Query("SELECT a FROM Annonce a WHERE a.valide = 'Approuve' AND a.internaute != ?1")
    Optional<List<Annonce>> findAnnoncesExceptInternaute(Internaute internaute);

    //Automobiliste------------------------------------------------------------------------------------------------------------
    @Query("SELECT a FROM Annonce a WHERE a.regulier = true AND a.internaute = ?1")
    Optional<List<Annonce>> findAnnoncesRegulierByInternaute(Internaute internaute);

    @Query("SELECT a FROM Annonce a WHERE a.regulier = false AND a.internaute = ?1")
    Optional<List<Annonce>> findAnnoncesPonctuelByInternaute(Internaute internaute);

    //Voyageur-----------------------------------------------------------------------------------------------------------------
    @Query("SELECT a FROM Annonce a WHERE a.valide = 'Approuve' AND a.statut = 'En cours' AND a.regulier = true AND a.internaute != ?1")
    Optional<List<Annonce>> findAnnoncesRegulierExceptInternaute(Internaute internaute);

    @Query("SELECT a FROM Annonce a WHERE a.valide = 'Approuve' AND a.statut = 'En cours' AND a.regulier = false AND a.internaute != ?1")
    Optional<List<Annonce>> findAnnoncesPonctuelExceptInternaute(Internaute internaute);

    //Admin--------------------------------------------------------------------------------------------------------------------
    @Query("SELECT a FROM Annonce a WHERE a.valide = ?1")
    Optional<List<Annonce>> findAnnoncesByValide(String valide);


    @Query("SELECT COUNT(a),a.statut FROM Annonce a GROUP BY a.statut")
    Object[] countAnnoncesBystatut();

    @Query("SELECT COUNT(a),MONTH(a.date_arrivee) FROM Annonce a WHERE a.statut = ?1 GROUP BY MONTH(a.date_arrivee) ORDER BY MONTH(a.date_arrivee)")
    Object[] countAnnoncesByOnestatut(String statut);

    @Query("SELECT COUNT(a) FROM Annonce a")
    int countAnnoncesTotal();
}
