package com.supmti.covoiturage.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "VEHICULE")
public class Vehicule implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id_vehicule")
    private long id_vehicule;

    @Column(name = "Matricule")
    private String matricule;

    @Column(name = "Marque")
    private String marque;

    @JsonFormat(pattern="yyyy-MM-dd", locale = "fr-FR", timezone = "Europe/Paris")
    @Column(name = "Date_fin_assurance")
    private Date date_fin_assurance;

    @Column(name = "Couleur")
    private String couleur;

    @Column(name = "Modele")
    private String modele;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "Id_internaute" , referencedColumnName = "Id")
    private Internaute internaute;

    @OneToMany(mappedBy = "vehicule")
    private Set<Annonce> annonce;


    public Vehicule() { }

    public Vehicule(long id_vehicule, String matricule, String marque, Date date_fin_assurance, String couleur, String modele, Internaute internaute) {
        this.id_vehicule = id_vehicule;
        this.matricule = matricule;
        this.marque = marque;
        this.date_fin_assurance = date_fin_assurance;
        this.couleur = couleur;
        this.modele = modele;
        this.internaute = internaute;
    }

    public long getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(long id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Date getDate_fin_assurance() {
        return date_fin_assurance;
    }

    public void setDate_fin_assurance(Date date_fin_assurance) {
        this.date_fin_assurance = date_fin_assurance;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Internaute getInternaute() {
        return internaute;
    }

    public void setInternaute(Internaute internaute) {
        this.internaute = internaute;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id_vehicule=" + id_vehicule +
                ", matricule='" + matricule + '\'' +
                ", marque='" + marque + '\'' +
                ", date_fin_assurance=" + date_fin_assurance +
                ", couleur='" + couleur + '\'' +
                ", modele='" + modele + '\'' +
                ", internaute=" + internaute +
                '}';
    }
}
