package com.supmti.covoiturage.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "INTERNAUTE")
public class Internaute extends User implements Serializable {
    @Column(name = "CIN")
    private String cin;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "Username")
    private String username;

    @Column(name = "Avatar")
    private String avatar;

    @JsonFormat(pattern="yyyy-MM-dd", locale = "fr-FR", timezone = "Europe/Paris")
    @Column(name = "Date_naiss")
    private Date date_naiss;

    @Column(name = "Sexe")
    private String sexe;

    @Column(name = "Tel")
    private String tel;

    @Column(name = "Verifie")
    private String verifie;



    @OneToOne(mappedBy = "internaute")
    private Vehicule vehicule;

    @OneToMany(mappedBy = "internaute")
    private Set<Annonce> annonce;

    @OneToMany(mappedBy = "internaute")
    private Set<Alert> alert;

    @OneToMany(mappedBy = "internaute")
    private Set<Reservation> reservations;

    public Internaute() { }

    public Internaute(long id, String email, String password, Date date_creation, String cin, String nom, String prenom, String avatar, Date date_naiss, String sexe,String tel, String verifie, String username, Vehicule vehicule, Set<Annonce> annonce, Set<Alert> alert, Set<Reservation> reservations) {
        super(id, email, password, date_creation);
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.avatar = avatar;
        this.date_naiss = date_naiss;
        this.sexe = sexe;
        this.tel = tel;
        this.verifie = verifie;
        this.username = username;
        this.vehicule = vehicule;
        this.annonce = annonce;
        this.alert = alert;
        this.reservations = reservations;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVerifie() {
        return verifie;
    }

    public void setVerifie(String verifie) {
        this.verifie = verifie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Vehicule getVehicule() {
//        return vehicule;
//    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

//    public Set<Annonce> getAnnonce() {
//        return annonce;
//    }

    public void setAnnonce(Set<Annonce> annonce) {
        this.annonce = annonce;
    }

//    public Set<Alert> getAlert() {
//        return alert;
//    }

    public void setAlert(Set<Alert> alert) {
        this.alert = alert;
    }

//    public Set<Reservation> getReservations() {
//        return reservations;
//    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Internaute{" +
                "cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", avatar='" + avatar + '\'' +
                ", date_naiss=" + date_naiss +
                ", sexe='" + sexe + '\'' +
                ", tel='" + tel + '\'' +
                ", verifie=" + verifie +
                ", username=" + username +
                '}';
    }
}
