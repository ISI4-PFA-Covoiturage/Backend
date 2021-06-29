package com.supmti.covoiturage.controller;

import com.supmti.covoiturage.model.Alert;
import com.supmti.covoiturage.model.Vehicule;
import com.supmti.covoiturage.service.AlertService;
import com.supmti.covoiturage.service.InternauteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private final InternauteService internauteService;
    private final AlertService alertService;



    public AlertController(InternauteService internauteService, AlertService alertService) {
        this.internauteService = internauteService;
        this.alertService = alertService;
    }


    @PostMapping("/add")
    public ResponseEntity<Alert> addAlert(@RequestBody Map<String, String> donnee){
        Alert alert = new Alert();

        alert.setDebut_trajet(donnee.get("debut_trajet"));
        alert.setArrivee_trajet(donnee.get("arrivee_trajet"));
        alert.setDate_debut(Date.valueOf(donnee.get("date_debut")));
        alert.setDate_arrivee(Date.valueOf(donnee.get("date_arrivee")));
        alert.setHeure_debut(java.sql.Time.valueOf(donnee.get("heure_debut")+ ":00"));
        alert.setHeure_arrivee(java.sql.Time.valueOf(donnee.get("heure_arrivee")+ ":00"));
        alert.setEtat("Non existant");

        alert.setInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        Alert newAlert = alertService.addAlert(alert);


        return new ResponseEntity<>(newAlert, HttpStatus.CREATED);
    }




}
