package com.commercial.gestion.model;

public class ArticleBonDeLivraison {
    private int idArticleBonDeLivraison;
    private int idBonDeLivraison;
    private int idArtilce;
    private int idUnite;
    private int quantite;

    public int getIdArticleBonDeLivraison() {
        return idArticleBonDeLivraison;
    }

    public void setIdArticleBonDeLivraison(int idArticleBonDeLivraison) {
        this.idArticleBonDeLivraison = idArticleBonDeLivraison;
    }

    public int getIdBonDeLivraison() {
        return idBonDeLivraison;
    }

    public void setIdBonDeLivraison(int idBonDeLivraison) {
        this.idBonDeLivraison = idBonDeLivraison;
    }

    public int getIdArtilce() {
        return idArtilce;
    }

    public void setIdArtilce(int idArtilce) {
        this.idArtilce = idArtilce;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public ArticleBonDeLivraison() {

    }

    public ArticleBonDeLivraison(int idArticleBonDeLivraison, int idBonDeLivraison, int idArtilce, int idUnite, int quantite) {
        this.setIdArticleBonDeLivraison(idArticleBonDeLivraison);
        this.setIdBonDeLivraison(idBonDeLivraison);
        this.setIdArtilce(idArtilce);
        this.setIdUnite(idUnite);
        this.setQuantite(quantite);
    }

    public boolean ajoutArticleBonDeLivraison() {
        return false;
    }
}
