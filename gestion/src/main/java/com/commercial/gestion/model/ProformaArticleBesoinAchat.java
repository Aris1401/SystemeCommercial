package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.aris.bdd.annotations.ExcludeFromInsertion;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class ProformaArticleBesoinAchat extends BDD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int idProformaBesoinAchat;

     int idProforma;

     int idArticleBesoinAchat;

    Timestamp dateLiaison;

    // Constructors, getters, and setters

    public ProformaArticleBesoinAchat() {
        // Default constructor
    }

    public ProformaArticleBesoinAchat(int idProforma, int idArticleBesoinAchat, Timestamp dateLivraison) {
        this.idProforma = idProforma;
        this.idArticleBesoinAchat = idArticleBesoinAchat;
        this.dateLiaison = dateLivraison;
    }

    // Getters and setters

    public int getIdProformaBesoinAchat() {
        return idProformaBesoinAchat;
    }

    public void setIdProformaBesoinAchat(int idProformaBesoinAchat) {
        this.idProformaBesoinAchat = idProformaBesoinAchat;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public int getIdArticleBesoinAchat() {
        return idArticleBesoinAchat;
    }

    public void setIdArticleBesoinAchat(int idArticleBesoinAchat) {
        this.idArticleBesoinAchat = idArticleBesoinAchat;
    }

    public Timestamp getDateLiaison() {
        return dateLiaison;
    }

    public void setDateLiaison(Timestamp dateLiaison) {
        this.dateLiaison = dateLiaison;
    }
}

