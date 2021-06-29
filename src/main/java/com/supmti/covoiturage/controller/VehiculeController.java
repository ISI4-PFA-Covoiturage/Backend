package com.supmti.covoiturage.controller;

import com.supmti.covoiturage.model.Reservation;
import com.supmti.covoiturage.model.Vehicule;
import com.supmti.covoiturage.service.InternauteService;
import com.supmti.covoiturage.service.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicule")
public class VehiculeController {
    private final VehiculeService vehiculeService;
    private final InternauteService internauteService;

    public VehiculeController(VehiculeService vehiculeService, InternauteService internauteService){
        this.vehiculeService = vehiculeService;
        this.internauteService = internauteService;
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Map<String, String> donnee){
           System.out.println(donnee);
        Vehicule FirstVehicule = new Vehicule();
        FirstVehicule.setInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        FirstVehicule.setMatricule(donnee.get("matricule"));
        FirstVehicule.setMarque(donnee.get("marque"));
        FirstVehicule.setDate_fin_assurance(Date.valueOf(donnee.get("date_fin_assurance")));
        FirstVehicule.setCouleur(donnee.get("couleur"));
        FirstVehicule.setModele(donnee.get("modele"));

        Vehicule newVehicule = vehiculeService.addVehicule(FirstVehicule);
        return new ResponseEntity<>(newVehicule, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Vehicule> updateVehicule(@RequestBody Map<String, String> donnee){
        System.out.println(donnee);
        Vehicule v = new Vehicule();
        v=vehiculeService.findVehiculeByInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        System.out.println(v);
        v.setMatricule(donnee.get("matricule"));
        v.setMarque(donnee.get("marque"));
        v.setDate_fin_assurance(Date.valueOf(donnee.get("date_fin_assurance")));
        v.setCouleur(donnee.get("couleur"));
        v.setModele(donnee.get("modele"));
        v.setInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        Vehicule updateV = vehiculeService.updateVehicule(v);
        return new ResponseEntity<>(updateV, HttpStatus.OK);
    }


    @GetMapping("/findByInternaute/{internaute}")
    public ResponseEntity<Vehicule> getVehiculeByInternaute(@PathVariable("internaute") Long id){
        Vehicule vehicule = vehiculeService.findVehiculeByInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(vehicule, HttpStatus.OK);
    }

    @GetMapping("/countByInternaute/{internaute}")
    public ResponseEntity<Integer> getCountVehiculeByInternaute(@PathVariable("internaute") Long id){
        int l = vehiculeService.countVehiculeByInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/dashboard/countVehiculeTotal")
    public int countVehiculeTotal(){
        int I = vehiculeService.countVehiculeTotal();
        return I;
    }

}
