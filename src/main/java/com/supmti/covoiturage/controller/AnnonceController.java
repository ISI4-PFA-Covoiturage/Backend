package com.supmti.covoiturage.controller;

import com.supmti.covoiturage.model.Annonce;
import com.supmti.covoiturage.service.AnnonceService;
import com.supmti.covoiturage.service.InternauteService;
import com.supmti.covoiturage.service.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/annonce")
public class AnnonceController {
    private final AnnonceService annonceService;
    private final InternauteService internauteService;
    private final VehiculeService vehiculeService;


    public AnnonceController(AnnonceService annonceService, InternauteService internauteService, VehiculeService vehiculeService) {
        this.annonceService = annonceService;
        this.internauteService = internauteService;
        this.vehiculeService = vehiculeService;
    }


    @GetMapping("all")
    public ResponseEntity<List<Annonce>> getAllAnnonces(){
        List<Annonce> annonces = annonceService.findAllAnnonces();
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable("id") Long id){
        Annonce annonce = annonceService.findAnnonceById(id);
        return new ResponseEntity<>(annonce, HttpStatus.OK);
    }

    @GetMapping("/findByInternaute/{internaute}")
    public ResponseEntity<List<Annonce>> getAnnonceByInternaute(@PathVariable("internaute") Long id){
        List<Annonce> annonces = annonceService.findAnnoncesByInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    @GetMapping("/findExceptInternaute/{internaute}")
    public ResponseEntity<List<Annonce>> getAnnoncesExceptId(@PathVariable("internaute") Long id){
        List<Annonce> annonces = annonceService.findAnnoncesExceptInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Annonce> addAnnonce(@RequestBody Map<String, String> donnee){
     //   System.out.println(donnee);
        Annonce FirstAnnonce = new Annonce();
        FirstAnnonce.setDebut_trajet(donnee.get("debut_trajet"));
        FirstAnnonce.setArrivee_trajet(donnee.get("arrivee_trajet"));
        FirstAnnonce.setNbr_place(Integer.parseInt(donnee.get("nbr_place")));
        if(donnee.containsKey("date_debut")==true)
        {FirstAnnonce.setDate_debut(Date.valueOf(donnee.get("date_debut")));}
        if(donnee.containsKey("date_arrivee")==true)
        {FirstAnnonce.setDate_arrivee(Date.valueOf(donnee.get("date_arrivee")));}
        if(donnee.containsKey("jour")==true)
        {FirstAnnonce.setJour(donnee.get("jour"));}
        FirstAnnonce.setHeure_debut(java.sql.Time.valueOf(donnee.get("heure_debut")+ ":00"));
        FirstAnnonce.setHeure_arrivee(java.sql.Time.valueOf(donnee.get("heure_arrivee")+ ":00"));
        FirstAnnonce.setRegulier(Boolean.parseBoolean(donnee.get("regulier")));
        FirstAnnonce.setFrais(Double.parseDouble(donnee.get("frais")));
        FirstAnnonce.setInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        FirstAnnonce.setVehicule(vehiculeService.findVehiculeById(Long.valueOf((donnee.get("vehicule")))));
        Annonce newAnnonce = annonceService.addAnnonce(FirstAnnonce);
        return new ResponseEntity<>(newAnnonce, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Annonce> updateAnnonce(@RequestBody Map<String, String> donnee){
           System.out.println(donnee);
        Annonce SameAnnonce = new Annonce();
        SameAnnonce.setId(Integer.parseInt(donnee.get("id")));
        SameAnnonce= annonceService.findAnnonceById(Long.parseLong(donnee.get("id")));
        SameAnnonce.setDebut_trajet(donnee.get("debut_trajet"));
        SameAnnonce.setArrivee_trajet(donnee.get("arrivee_trajet"));
        SameAnnonce.setNbr_place(Integer.parseInt(donnee.get("nbr_place")));
        /*
        if(donnee.containsKey("date_debut")==true)
        {SameAnnonce.setDate_debut(Date.valueOf(donnee.get("date_debut")));}
        if(donnee.containsKey("date_arrivee")==true)
        {SameAnnonce.setDate_arrivee(Date.valueOf(donnee.get("date_arrivee")));}
        if(donnee.containsKey("jour")==true)
        {SameAnnonce.setJour(donnee.get("jour"));}

         */
        SameAnnonce.setHeure_debut(java.sql.Time.valueOf(donnee.get("heure_debut")+ ":00"));
        SameAnnonce.setHeure_arrivee(java.sql.Time.valueOf(donnee.get("heure_arrivee")+ ":00"));
        SameAnnonce.setRegulier(Boolean.parseBoolean(donnee.get("regulier")));
        SameAnnonce.setFrais(Double.parseDouble(donnee.get("frais")));
        SameAnnonce.setValide("En Attend");
        SameAnnonce.setStatut(donnee.get("statut"));
        SameAnnonce.setInternaute(internauteService.findInternauteById(Long.valueOf(donnee.get("internaute"))));
        SameAnnonce.setVehicule(vehiculeService.findVehiculeById(Long.valueOf(donnee.get("vehicule"))));
        Annonce updateAnnonce = annonceService.updateAnnonce(SameAnnonce);
        return new ResponseEntity<>(updateAnnonce, HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable("id") Long id){
        Annonce SameAnnonce = annonceService.findAnnonceById(id);
        SameAnnonce.setStatut("Supprimer");

        Annonce SupprimerAnnonce = annonceService.updateAnnonce(SameAnnonce);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Automobiliste-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/findRegulierInternaute/{internaute}")
    public ResponseEntity<List<Annonce>> getAnnoncesRegulierId(@PathVariable("internaute") Long id){
        List<Annonce> annonces = annonceService.findAnnoncesRegulierByInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    @GetMapping("/findPonctuelInternaute/{internaute}")
    public ResponseEntity<List<Annonce>> getAnnoncesPonctuelId(@PathVariable("internaute") Long id){
        List<Annonce> annonces = annonceService.findAnnoncesPonctuelByInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }
    //Voyageur-----------------------------------------------------------------------------------------------------------------
    @GetMapping("/findRegulierExceptInternaute/{internaute}")
    public ResponseEntity<List<Annonce>> getAnnoncesRegulierExceptId(@PathVariable("internaute") Long id){
        List<Annonce> annonces = annonceService.findAnnoncesRegulierExceptInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    @GetMapping("/findPonctuelExceptInternaute/{internaute}")
    public ResponseEntity<List<Annonce>> getAnnoncesPonctuelExceptId(@PathVariable("internaute") Long id){
        List<Annonce> annonces = annonceService.findAnnoncesPonctuelExceptInternaute(internauteService.findInternauteById(id));
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }

    //Admin------------------------------------------------------------------------------------------------------------------

    @GetMapping("/findByValide/{valide}")
    public ResponseEntity<List<Annonce>> getAnnoncesByValide(@PathVariable("valide") String valide){
        List<Annonce> annonces = annonceService.findAnnoncesByValide(valide);
        return new ResponseEntity<>(annonces, HttpStatus.OK);
    }


    @PutMapping("/updateValide/{valide}/{id}")
    public ResponseEntity<Annonce> updateAnnonceValide(@PathVariable("valide") String valide, @PathVariable("id") Long id){
        Annonce sameAnnonce = new Annonce();
        System.out.println("Before");
        sameAnnonce = annonceService.findAnnonceById(id);
        sameAnnonce.setValide(valide);
        annonceService.updateAnnonce(sameAnnonce);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateStatut/{statut}/{id}")
    public ResponseEntity<Annonce> updateAnnonceStatut(@PathVariable("statut") String statut, @PathVariable("id") Long id){
        Annonce sameAnnonce = new Annonce();
        System.out.println("Before");
        sameAnnonce = annonceService.findAnnonceById(id);
        sameAnnonce.setStatut(statut);



        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        System.out.println(date);
        sameAnnonce.setDate_arrivee(date);

        annonceService.updateAnnonce(sameAnnonce);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Dashboard
    @GetMapping("/dashboard/countAnnoncesBystatut")
    public Object[] countAnnoncesBystatut(){

        Object[] M = annonceService.countAnnoncesBystatut();
                return M;
    }

    @GetMapping("/dashboard/countAnnoncesByOnestatut/{statut}")
    public Object[] countAnnoncesByOnestatut(@PathVariable("statut") String statut){
        Object[] O = annonceService.countAnnoncesByOnestatut(statut);
        return O;
    }

    @GetMapping("/dashboard/countAnnoncesTotal")
    public int countAnnoncesTotal(){
        int I = annonceService.countAnnoncesTotal();
        return I;
    }

}
