package com.supmti.covoiturage.model;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Statut")
    private String statut;

    @Column(name = "Remarque")
    private String remarque;

    @Column(name = "Notation")
    private int notation;

    @ManyToOne
    @JoinColumn(name = "Id_internaute" , referencedColumnName = "Id")
    private Internaute internaute;

    @ManyToOne
    @JoinColumn(name = "Id_annonce" , referencedColumnName = "Id_annonce")
    private Annonce annonce;

    public Reservation() { }

    public Reservation(long id, String statut, String remarque, int notation, Internaute internaute, Annonce annonce) {
        this.id = id;
        this.statut = statut;
        this.remarque = remarque;
        this.notation = notation;
        this.internaute = internaute;
        this.annonce = annonce;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getNotation() {
        return notation;
    }

    public void setNotation(int notation) {
        this.notation = notation;
    }

    public Internaute getInternaute() {
        return internaute;
    }

    public void setInternaute(Internaute internaute) {
        this.internaute = internaute;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", statut='" + statut + '\'' +
                ", remarque='" + remarque + '\'' +
                ", notation=" + notation +
                ", internaute=" + internaute +
                ", annonce=" + annonce +
                '}';
    }
}
