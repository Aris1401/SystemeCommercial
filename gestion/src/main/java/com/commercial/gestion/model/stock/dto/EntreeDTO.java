package com.commercial.gestion.model.stock.dto;

import java.sql.Timestamp;

public class EntreeDTO {
    double entree;
    Timestamp dateMouvement;
    int idArticle;
    int idUnite;
    double prixUnitaire;

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public Timestamp getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Timestamp dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
