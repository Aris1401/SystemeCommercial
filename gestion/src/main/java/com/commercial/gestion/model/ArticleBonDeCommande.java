/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class ArticleBonDeCommande extends BDD
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
 //////////////////////////////////////////////////////////////////
 public ArticleBonDeCommande getArticleBonDeCommandeById(int idArticleBonDeCommande)
 {
     ArticleBonDeCommande a=new ArticleBonDeCommande();
     String condition="idArticleBonDeCommande="+idArticleBonDeCommande;
     ArrayList<String[]>allArticleBonDeCammande=a.select(condition);
     for(int i=0;i< allArticleBonDeCammande.size();i++)
     {
         a.setIdArticleBonDeCommande(Integer.parseInt(allArticleBonDeCammande.get(i)[0]));
         a.setIdArticle(Integer.parseInt(allArticleBonDeCammande.get(i)[1]));
         a.setQuantite(Double.parseDouble(allArticleBonDeCammande.get(i)[2]));
         a.setPrixUnitaireHT(Double.parseDouble(allArticleBonDeCammande.get(i)[3]));
         a.setTVA(Double.parseDouble(allArticleBonDeCammande.get(i)[4]));
         a.setIdBonDeCommande(Integer.parseInt(allArticleBonDeCammande.get(i)[5]));
     }
     return a;
 }
//////////////////////////////////////////////////////////////////
public ArticleBonDeCommande getArticleBonDeCommandeForArticle(int idArticle)
{
    ArticleBonDeCommande a=new ArticleBonDeCommande();
    String condition="idArticle="+idArticle;
    ArrayList<String[]>allArticleBonDeCammande=a.select(condition);
    for(int i=0;i< allArticleBonDeCammande.size();i++)
    {
        a.setIdArticleBonDeCommande(Integer.parseInt(allArticleBonDeCammande.get(i)[0]));
        a.setIdArticle(Integer.parseInt(allArticleBonDeCammande.get(i)[1]));
        a.setQuantite(Double.parseDouble(allArticleBonDeCammande.get(i)[2]));
        a.setPrixUnitaireHT(Double.parseDouble(allArticleBonDeCammande.get(i)[3]));
        a.setTVA(Double.parseDouble(allArticleBonDeCammande.get(i)[4]));
        a.setIdBonDeCommande(Integer.parseInt(allArticleBonDeCammande.get(i)[5]));
    }
    return a;
}
//////////////////////////////////////////////////////////////////
    public static ArticleBonDeCommande createArticleBonDeCommande(ArticleBesoinAchat articleBesoinAchat) {
        return null;
    }
}
