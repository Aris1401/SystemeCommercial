package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import jakarta.persistence.*;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "ProformaArticleBesoinAchat")
public class ProformaArticleBesoinAchat extends BDD
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProformaBesoinAchat")
    private Long idProformaBesoinAchat;

    private int idProforma;

    private int idArticleBesoinAchat;

      @Column(name = "dateLivraison")
    private Date dateLivraison;

    // Constructors, getters, and setters

    public ProformaArticleBesoinAchat() {
        // Default constructor
    }

    public ProformaArticleBesoinAchat(int idProforma, int idArticleBesoinAchat, Date dateLivraison) {
        this.idProforma = idProforma;
        this.idArticleBesoinAchat = idArticleBesoinAchat;
        this.dateLivraison = dateLivraison;
    }

    // Getters and setters

    public Long getIdProformaBesoinAchat() {
        return idProformaBesoinAchat;
    }

    public void setIdProformaBesoinAchat(Long idProformaBesoinAchat) {
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

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

}

