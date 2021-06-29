package com.supmti.covoiturage.service;

import com.supmti.covoiturage.exception.VehiculeNotFoundException;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.model.Vehicule;
import com.supmti.covoiturage.repo.VehiculeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VehiculeService {
    private final VehiculeRepo vehiculeRepo;

    @Autowired
    public VehiculeService(VehiculeRepo vehiculeRepo){this.vehiculeRepo = vehiculeRepo;}

    public Vehicule addVehicule(Vehicule vehicule)
    {
        return vehiculeRepo.save(vehicule);
    }

    public Vehicule updateVehicule(Vehicule vehicule){return  vehiculeRepo.save(vehicule);}

    public Vehicule findVehiculeById(Long id){
        return vehiculeRepo.findVehiculeById(id).orElseThrow(()-> new VehiculeNotFoundException("Vehicule by id"+id+"was not found"));
    }

    public Vehicule findVehiculeByInternaute(Internaute internaute){
        return vehiculeRepo.findVehiculeByInternaute(internaute).orElse(null);
    }

    public int countVehiculeByInternaute(Internaute internaute){
        return vehiculeRepo.countVehiculeByInternaute(internaute);
    }

    public int countVehiculeTotal(){
        return vehiculeRepo.countVehiculeTotal();
    }
}
