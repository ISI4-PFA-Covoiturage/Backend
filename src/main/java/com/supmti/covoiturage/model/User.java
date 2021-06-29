package com.supmti.covoiturage.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id",nullable = false,updatable = false)
    private long id;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;

    @JsonFormat(pattern="yyyy-MM-dd", locale = "fr-FR", timezone = "Europe/Paris")
    @Column(name = "Date_creation")
    private Date date_creation;

    public User() { }

    public User(long id, String email, String password, Date date_creation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.date_creation = date_creation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date_creation=" + date_creation +
                '}';
    }
}
