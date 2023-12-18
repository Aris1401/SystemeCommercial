package com.commercial.gestion.model;

import java.sql.Timestamp;

public class ArticleBonDeReception {
    private int idArticleBonDeReception;
    private int idArtilce;
    private int idBonDeReception;
    private int quantite;
    private int idUnite;

    public int getIdArticleBonDeReception() {
        return idArticleBonDeReception;
    }

    public void setIdArticleBonDeReception(int idArticleBonDeReception) {
        this.idArticleBonDeReception = idArticleBonDeReception;
    }

    public int getIdArtilce() {
        return idArtilce;
    }

    public void setIdArtilce(int idArtilce) {
        this.idArtilce = idArtilce;
    }

    public int getIdBonDeReception() {
        return idBonDeReception;
    }

    public void setIdBonDeReception(int idBonDeReception) {
        this.idBonDeReception = idBonDeReception;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public ArticleBonDeReception() {

    }

    public ArticleBonDeReception(int idArticleBonDeReception, int idArtilce, int idBonDeReception, int quantite, int idUnite) {
        this.setIdArticleBonDeReception(idArticleBonDeReception);
        this.setIdArtilce(idArtilce);
        this.setIdBonDeReception(idBonDeReception);
        this.setQuantite(quantite);
        this.setIdUnite(idUnite);
    }

    public boolean ajoutArticleBonDeReception() {
        return false;
    }
}
