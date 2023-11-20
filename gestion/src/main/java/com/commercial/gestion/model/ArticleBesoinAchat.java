/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author BEST
 */
public class ArticleBesoinAchat extends BDD {
    int idArticleBesoinAchat;
    int idArticle;
    int idBesoinAchat;
    double quantite;
    int idUnite;
    String descriptionArticleBesoin;

    public ArticleBesoinAchat() {}

    public ArticleBesoinAchat(ArticleBesoinAchat other) {
        this.idArticleBesoinAchat = other.idArticleBesoinAchat;
        this.idArticle = other.idArticle;
        this.idBesoinAchat = other.idBesoinAchat;
        this.quantite = other.quantite;
        this.idUnite = other.idUnite;
        this.descriptionArticleBesoin = other.descriptionArticleBesoin;
    }

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

    public static ArrayList<ArticleBesoinAchat> getArticleBesoinAchatByBesoinAchat(int idBesoinAchat) {
        ArticleBesoinAchat a = new ArticleBesoinAchat();
        String condition = "where idBesoinAchat = " + idBesoinAchat;

        ArrayList<ArticleBesoinAchat> articleBesoinAchats = new ArrayList<>();

        ArrayList<String[]> allArticleBDD = a.select(condition);
        for (int i = 0; i < allArticleBDD.size(); i++) {
            ArticleBesoinAchat article = new ArticleBesoinAchat();
            article.setIdArticleBesoinAchat(Integer.parseInt(allArticleBDD.get(i)[0]));
            article.setIdArticle(Integer.parseInt(allArticleBDD.get(i)[1]));
            article.setIdBesoinAchat(Integer.parseInt(allArticleBDD.get(i)[2]));
            article.setQuantite(Double.parseDouble(allArticleBDD.get(i)[3]));
            article.setIdUnite(Integer.parseInt(allArticleBDD.get(i)[4]));
            article.setDescriptionArticleBesoin(allArticleBDD.get(i)[5]);

            articleBesoinAchats.add(article);
        }
        return articleBesoinAchats;
    }

    public Article getArticle() {
        return Article.getArticleById(idArticle);
    }

    public Unite getUnite() {
        GenericDAO<Unite> uniteGenericDAO = new GenericDAO<>(Unite.class);

        try {
            Connection connection = ConnectTo.postgreS();

            uniteGenericDAO.addToSelection("idUnite", idUnite, "");
            ArrayList<Unite> unites = uniteGenericDAO.getFromDatabase(connection);

            connection.close();

            return unites.isEmpty() ? null : unites.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Proforma> obtenirProformas() {
        return Proforma.obtenirProformaBesoinArticle(idArticleBesoinAchat);
    }

    public double getEstimationPrix() {
        double estimation = 0;

        ArrayList<Proforma> proformas = Proforma.getMoinsDixAns(obtenirProformas(), idArticleBesoinAchat);
        for (Proforma proforma : proformas) {
            estimation += proforma.getPrixUnitaire();
        }

        estimation /= proformas.size();

        return estimation;
    }
}
