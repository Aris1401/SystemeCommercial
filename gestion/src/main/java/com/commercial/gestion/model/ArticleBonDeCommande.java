/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

/**
 *
 * @author BEST
 */
public class ArticleBonDeCommande 
{
    int idArticleBonDeCommande;
    int idArticle;
    double quantite;
    double prixUnitaireHT;
    double TVA;
    int idBonDeCommande;

    public int getIdArticleBonDeCommande() {
        return idArticleBonDeCommande;
    }

    public void setIdArticleBonDeCommande(int idArticleBonDeCommande) {
        this.idArticleBonDeCommande = idArticleBonDeCommande;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    public void setPrixUnitaireHT(double prixUnitaireHT) {
        this.prixUnitaireHT = prixUnitaireHT;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    public int getIdBonDeCommande() {
        return idBonDeCommande;
    }

    public void setIdBonDeCommande(int idBonDeCommande) {
        this.idBonDeCommande = idBonDeCommande;
    }
    
}
