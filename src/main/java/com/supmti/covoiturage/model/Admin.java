package com.supmti.covoiturage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ADMIN")
public class Admin extends User implements Serializable {
    @Column(name = "Username")
    private String username;

    public Admin() { }

    public Admin(long id, String email, String password, Date date_creation, String username) {
        super(id, email, password, date_creation);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                '}';
    }


}
