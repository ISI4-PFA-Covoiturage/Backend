package com.supmti.covoiturage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.supmti.covoiturage.TimeDeserializer.SqlTimeDeserializer;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "ALERT")
public class Alert {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id_alert")
    private long id_alert;

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

    @Column(name = "Debut_trajet")
    private String debut_trajet;

    @Column(name = "Arrivee_trajet")
    private String arrivee_trajet;

    @Column(name = "Etat")
    private String etat;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_internaute" , referencedColumnName = "Id")
    private Internaute internaute;


    public Alert() { }

    public Alert(long id_alert, Date date_debut, Date date_arrivee, Time heure_debut, Time heure_arrivee, String debut_trajet, String arrivee_trajet, String etat, Internaute internaute) {
        this.id_alert = id_alert;
        this.date_debut = date_debut;
        this.date_arrivee = date_arrivee;
        this.heure_debut = heure_debut;
        this.heure_arrivee = heure_arrivee;
        this.debut_trajet = debut_trajet;
        this.arrivee_trajet = arrivee_trajet;
        this.etat = etat;
        this.internaute = internaute;
    }

    public long getId_alert() {
        return id_alert;
    }

    public void setId_alert(long id_alert) {
        this.id_alert = id_alert;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_arrivee() {
        return date_arrivee;
    }

    public void setDate_arrivee(Date date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public Time getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public Time getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(Time heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Internaute getInternaute() {
        return internaute;
    }

    public void setInternaute(Internaute internaute) {
        this.internaute = internaute;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id_alert=" + id_alert +
                ", date_debut=" + date_debut +
                ", date_arrivee=" + date_arrivee +
                ", heure_debut=" + heure_debut +
                ", heure_arrivee=" + heure_arrivee +
                ", debut_trajet='" + debut_trajet + '\'' +
                ", arrivee_trajet='" + arrivee_trajet + '\'' +
                ", etat='" + etat + '\'' +
                ", internaute=" + internaute +
                '}';
    }
}
