package com.supmti.covoiturage.controller;

import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.service.InternauteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/internaute")
public class InternauteController {
    private final InternauteService internauteService;

    public InternauteController(InternauteService internauteService) {
        this.internauteService = internauteService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Internaute>> getAllInternautes(){
    List<Internaute> internautes = internauteService.findAllInternautes();
        return new ResponseEntity<>(internautes, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Internaute> updateInternaute(@RequestBody Map<String, String> donnee){
        System.out.println(donnee);
        Internaute internaute = new Internaute();
        internaute = internauteService.findInternauteById(Long.valueOf(donnee.get("id")));
        System.out.println("before id");
        System.out.println(internaute);
        System.out.println("before nom");
        internaute.setNom(donnee.get("nom"));
        System.out.println("before date");
        System.out.println(Date.valueOf(donnee.get("date_naiss")));
        internaute.setDate_naiss(Date.valueOf(donnee.get("date_naiss")));
        System.out.println("before tel");
        internaute.setTel(donnee.get("tel"));
        System.out.println("before prenom");
        internaute.setPrenom(donnee.get("prenom"));
        System.out.println("before sexe");
        internaute.setSexe(donnee.get("sexe"));
        Internaute updateInternaute = internauteService.updateInternaute(internaute);
        return new ResponseEntity<>(updateInternaute, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Internaute> getInternautesById(@PathVariable("id") Long id){
        Internaute internaute = internauteService.findInternauteById(id);
        return new ResponseEntity<>(internaute, HttpStatus.OK);
    }

    //Admin------------------------------------------------------------------------------------------------------------------

    @GetMapping("/findByVerifie/{verifie}")
    public ResponseEntity<List<Internaute>> getInternautesByVerifie(@PathVariable("verifie") String verifie){
        List<Internaute> internautes = internauteService.findInternautesByVerifie(verifie);
        return new ResponseEntity<>(internautes, HttpStatus.OK);
    }

    @PutMapping("/updateVerifie/{verifie}/{id}")
    public ResponseEntity<Internaute> updateInternauteVerifie(@PathVariable("verifie") String verifie, @PathVariable("id") Long id){
        Internaute sameInternaute = new Internaute();
        System.out.println("Before");
        sameInternaute = internauteService.findInternauteById(id);
        sameInternaute.setVerifie(verifie);
        internauteService.updateInternaute(sameInternaute);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Dashboard
    @GetMapping("/dashboard/countInternauteByDateCreation/{verifie}")
    public Object[] countInternauteByDateCreation(@PathVariable("verifie") String verifie) {
        Object[] O = internauteService.countInternauteByDateCreation(verifie);
        return O;
    }

    //number Navbar
    @GetMapping("/dashboard/countInternauteTotal")
    public int countInternauteTotal() {
        int I = internauteService.countInternauteTotal();
        return I;
    }
}
