package com.commercial.gestion.model.stock.dto;

import java.sql.Timestamp;

public class SortieDTO {
    double sortie;
    Timestamp dateMouvement;
    int idArticle;
    int idUnite;

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
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
}
