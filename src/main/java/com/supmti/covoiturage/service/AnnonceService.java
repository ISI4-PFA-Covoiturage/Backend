package com.supmti.covoiturage.service;

import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.repo.AnnonceRepo;
import com.supmti.covoiturage.exception.AnnonceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AnnonceService {
    private final AnnonceRepo annonceRepo;

    @Autowired
    public AnnonceService(AnnonceRepo annonceRepo) {
        this.annonceRepo = annonceRepo;
    }

    public Annonce addAnnonce(Annonce annonce){
        annonce.setStatut("En cours");
        annonce.setValide("En Attend");
        return annonceRepo.save(annonce);
    }

    public List<Annonce> findAllAnnonces(){return annonceRepo.findAll();}

    public Annonce updateAnnonce(Annonce annonce){return  annonceRepo.save(annonce);}

    public  Annonce findAnnonceById(Long id){ return annonceRepo.findAnnonceById(id)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonce by id"+id+"was not found"));
    }

    public List<Annonce> findAnnoncesByInternaute(Internaute internaute){return annonceRepo.findAnnoncesByInternaute(internaute)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonce by Internaute"+internaute+"was not found"));

    }

    public void deleteAnnonce(Long id){annonceRepo.deleteAnnonceById(id);}

    //Automobiliste
    public List<Annonce> findAnnoncesRegulierByInternaute(Internaute internaute){return annonceRepo.findAnnoncesRegulierByInternaute(internaute)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonces Regulier by Internaute"+internaute+"was not found"));

    }

    public List<Annonce> findAnnoncesPonctuelByInternaute(Internaute internaute){return annonceRepo.findAnnoncesPonctuelByInternaute(internaute)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonces Ponctuel by Internaute"+internaute+"was not found"));

    }

    //Voyageur
    public List<Annonce> findAnnoncesExceptInternaute(Internaute internaute){return annonceRepo.findAnnoncesExceptInternaute(internaute)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonces except by id"+internaute+"was not found"));
    }
    public List<Annonce> findAnnoncesRegulierExceptInternaute(Internaute internaute){return annonceRepo.findAnnoncesRegulierExceptInternaute(internaute)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonces except by id"+internaute+"was not found"));
    }

    public List<Annonce> findAnnoncesPonctuelExceptInternaute(Internaute internaute){return annonceRepo.findAnnoncesPonctuelExceptInternaute(internaute)
            .orElseThrow(()-> new AnnonceNotFoundException("Annonces except by id"+internaute+"was not found"));
    }

    //Admin

        public List<Annonce> findAnnoncesByValide(String valide){
        return annonceRepo.findAnnoncesByValide(valide).orElseThrow(()-> new AnnonceNotFoundException("Annonce by valide"+valide+"was not found"));
    }

    public Object[] countAnnoncesBystatut(){
        return annonceRepo.countAnnoncesBystatut();
    }

    public Object[] countAnnoncesByOnestatut(String statut){
        return annonceRepo.countAnnoncesByOnestatut(statut);
    }

    public int countAnnoncesTotal(){
        return annonceRepo.countAnnoncesTotal();
    }


}
