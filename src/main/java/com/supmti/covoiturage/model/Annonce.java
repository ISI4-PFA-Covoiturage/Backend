package com.supmti.covoiturage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.supmti.covoiturage.TimeDeserializer.SqlTimeDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ANNONCE")
public class Annonce implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id_annonce")
    private long id;

    @Column(name = "Debut_trajet")
    private String debut_trajet;

    @Column(name = "Arrivee_trajet")
    private String arrivee_trajet;

    @Column(name = "Nbr_place")
    private int nbr_place;

    @JsonFormat(pattern="yyyy-MM-dd", locale = "fr-FR", timezone = "Europe/Paris")
    @Column(name = "Date_debut")
    private java.sql.Date date_debut;

    @JsonFormat(pattern="yyyy-MM-dd", locale = "fr-FR", timezone = "Europe/Paris")
    @Column(name = "Date_arrivee")
    private java.sql.Date date_arrivee;

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "Heure_debut")
    private java.sql.Time heure_debut;

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column(name = "Heure_arrivee")
    private java.sql.Time heure_arrivee;

    @Column(name = "Frais")
    private double frais;

    @Column(name = "Regulier")
    private boolean regulier;

    @Column(name = "Jour")
    private String jour;

    @Column(name = "Valide")
    private String valide;

    @Column(name = "Statut")
    private String statut;


    @ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "Id_internaute" , referencedColumnName = "Id")
    private Internaute internaute;

    @ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "Id_vehicule" , referencedColumnName = "Id_vehicule")
    private Vehicule vehicule;

    @OneToMany(mappedBy = "annonce")
    private Set<Reservation> reservations;

    public Annonce() { }

    public Annonce(long id, String debut_trajet, String arrivee_trajet, int nbr_place, java.sql.Date date_debut, java.sql.Date date_arrivee, java.sql.Time heure_debut, java.sql.Time heure_arrivee, double frais, boolean regulier, String jour, String valide, String statut, Internaute internaute, Vehicule vehicule, Set<Reservation> reservations) {
        this.id = id;
        this.debut_trajet = debut_trajet;
        this.arrivee_trajet = arrivee_trajet;
        this.nbr_place = nbr_place;
        this.date_debut = date_debut;
        this.date_arrivee = date_arrivee;
        this.heure_debut = heure_debut;
        this.heure_arrivee = heure_arrivee;
        this.frais = frais;
        this.regulier = regulier;
        this.jour = jour;
        this.valide = valide;
        this.statut = statut;
        this.internaute = internaute;
        this.vehicule = vehicule;
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDebut_trajet() {
        return debut_trajet;
    }

    public void setDebut_trajet(String debut_trajet) {
        this.debut_trajet = debut_trajet;
    }

    public String getArrivee_trajet() {
        return arrivee_trajet;
    }

    public void setArrivee_trajet(String arrivee_trajet) {
        this.arrivee_trajet = arrivee_trajet;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public java.sql.Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(java.sql.Date date_debut) {
        this.date_debut = date_debut;
    }

    public java.sql.Date getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(java.sql.Date date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public java.sql.Time getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(java.sql.Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public java.sql.Time getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(java.sql.Time heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public boolean isRegulier() {
        return regulier;
    }

    public void setRegulier(boolean regulier) {
        this.regulier = regulier;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Internaute getInternaute() {
        return internaute;
    }

    public void setInternaute(Internaute internaute) {
        this.internaute = internaute;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    //    public Set<Reservation> getReservations() {
//        return reservations;
//    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", debut_trajet='" + debut_trajet + '\'' +
                ", arrivee_trajet='" + arrivee_trajet + '\'' +
                ", nbr_place=" + nbr_place +
                ", date_debut=" + date_debut +
                ", date_arrivee=" + date_arrivee +
                ", heure_debut=" + heure_debut +
                ", heure_arrivee=" + heure_arrivee +
                ", frais=" + frais +
                ", regulier=" + regulier +
                ", jour='" + jour + '\'' +
                ", valide=" + valide +
                ", statut='" + statut + '\'' +
                ", internaute=" + internaute +
                ", vehicule=" + vehicule +
                '}';
    }
}
