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
public class ArticleBesoinAchat extends BDD
{
   int idArticleBesoinAchat;
   int idArticle;
   int idBesoinAchat;
   double quantite;
   int idUnite;
   String descriptionArticleBesoin;

    public int getIdArticleBesoinAchat() {
        return idArticleBesoinAchat;
    }

    public void setIdArticleBesoinAchat(int idArticleBesoinAchat) {
        this.idArticleBesoinAchat = idArticleBesoinAchat;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getDescriptionArticleBesoin() {
        return descriptionArticleBesoin;
    }

    public void setDescriptionArticleBesoin(String descriptionArticleBesoin) {
        this.descriptionArticleBesoin = descriptionArticleBesoin;
    }
////////////////////////////////////////////////////////////
public static ArticleBesoinAchat getArticleBesoinAchatById(int idArticleBesoinAchat) {
    ArticleBesoinAchat a = new ArticleBesoinAchat();
    String condition = "";
    ArrayList<String[]> allArticleBDD = a.select();
    for (int i = 0; i < allArticleBDD.size(); i++) {

        a.setIdArticleBesoinAchat(Integer.parseInt(allArticleBDD.get(i)[0]));
        a.setIdArticle(Integer.parseInt(allArticleBDD.get(i)[1]));
        a.setIdBesoinAchat(Integer.parseInt(allArticleBDD.get(i)[2]));
        a.setQuantite(Double.parseDouble(allArticleBDD.get(i)[3]));
        a.setIdUnite(Integer.parseInt(allArticleBDD.get(i)[4]));
        a.setDescriptionArticleBesoin(allArticleBDD.get(i)[5]);
    }
    return a;
}
}
