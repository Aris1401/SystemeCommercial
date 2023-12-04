package com.commercial.gestion.model;

public class ArticleFacture {
    private int idArticleFacture;
    private int idFacture;
    private int idArticle;
    private int idUnite;
    private int quantite;
    private double prixUnitaireHT;

    public int getIdArticleFacture() {
        return idArticleFacture;
    }

    public void setIdArticleFacture(int idArticleFacture) {
        this.idArticleFacture = idArticleFacture;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    public void setPrixUnitaireHT(double prixUnitaireHT) {
        this.prixUnitaireHT = prixUnitaireHT;
    }

    public ArticleFacture(){

    }

    public ArticleFacture(int idArticleFacture, int idFacture, int idArticle, int idUnite, int quantite, double prixUnitaireHT) {
        this.setIdArticleFacture(idArticleFacture);
        this.setIdFacture(idFacture);
        this.setIdArticle(idArticle);
        this.setIdUnite(idUnite);
        this.setQuantite(quantite);
        this.setPrixUnitaireHT(prixUnitaireHT);
    }
}
