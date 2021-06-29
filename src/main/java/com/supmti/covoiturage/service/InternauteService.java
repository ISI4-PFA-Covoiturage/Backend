package com.supmti.covoiturage.service;

import com.supmti.covoiturage.exception.InternauteNotFoundException;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.repo.InternauteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InternauteService {
    private final InternauteRepo internauteRepo;

    @Autowired
    public InternauteService(InternauteRepo internauteRepo) {
        this.internauteRepo = internauteRepo;
    }

    public List<Internaute> findAllInternautes(){return internauteRepo.findAll();}


    public Internaute updateInternaute(Internaute internaute){return  internauteRepo.save(internaute);}


    public Internaute findInternauteById(Long id){
        return internauteRepo.findInternauteById(id).orElseThrow(()-> new InternauteNotFoundException("Internaute by id"+id+"was not found"));
    }
    public Internaute findInternauteByUsername(String username){
        return internauteRepo.findInternauteByUsername(username).orElseThrow(()-> new InternauteNotFoundException("Internaute by username"+username+"was not found"));
    }
    public List<Internaute> findInternautesByVerifie(String verifie){
        return internauteRepo.findInternautesByVerifie(verifie).orElseThrow(()-> new InternauteNotFoundException("Internaute by verifie"+verifie+"was not found"));
    }

    //Admin Dashboard
    public Object[] countInternauteByDateCreation(String verifie) {
        return internauteRepo.countInternauteByDateCreation(verifie);
    }

    //number navbar
    public int  countInternauteTotal() {
        return internauteRepo.countInternauteTotal();
    }
}

