/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.response.ArticleQuantiteResponse;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author BEST
 */
@Component
public class BesoinAchat extends BDD {
    int idBesoinAchat;
    int idService;
    Timestamp dateBesoin;
    Timestamp dateCloture;
    String description;
    int statusBesoin;

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public Timestamp getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Timestamp dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public Timestamp getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Timestamp dateCloture) {
        this.dateCloture = dateCloture;
    }

    public int getStatusBesoin() {
        return statusBesoin;
    }

    public void setStatusBesoin(int statusBesoin) {
        this.statusBesoin = statusBesoin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean insertBesoinAchat(String idService, String dateBesoin, String dateCloture, String statusBesoin) {
        boolean insert = false;
        BesoinAchat besoin = new BesoinAchat();
        besoin.setIdService(Integer.parseInt(idService));
        besoin.setDateBesoin(Timestamp.valueOf(dateBesoin));
        besoin.setDateCloture(Timestamp.valueOf(dateCloture));
        besoin.setStatusBesoin(Integer.parseInt(statusBesoin));
        besoin.dontSave("idBesoinAchat");
        besoin.save();
        insert = true;
        return insert;

    }

    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<BesoinAchat> allOpenBesoinAchat() {
        String condition = "where statusBesoin = " + String.valueOf(DemandeConfiguration.EN_COURS);
        BesoinAchat besoinAchat = new BesoinAchat();
        ArrayList<String[]> allBesoinAchatBDD = besoinAchat.select(condition);
        ArrayList<BesoinAchat> allBesoinAchat = new ArrayList<BesoinAchat>();
        for (String[] strings : allBesoinAchatBDD) {
            BesoinAchat b = new BesoinAchat();
            b.setIdBesoinAchat(Integer.parseInt(strings[0]));
            b.setIdService(Integer.parseInt(strings[1]));
            b.setDateBesoin(Timestamp.valueOf(strings[2]));
            b.setDateCloture(Timestamp.valueOf(strings[3]));
            b.setDescription(strings[5]);
            b.setStatusBesoin(Integer.parseInt(strings[4]));
            allBesoinAchat.add(b);
        }
        return allBesoinAchat;
    }

    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<BesoinAchat> allClosedBesoinAchat() {
        String condition = "where statusBesoin = " + String.valueOf(DemandeConfiguration.VALIDER) + " or statusBesoin = " + String.valueOf(DemandeConfiguration.REFUSER);
        BesoinAchat besoinAchat = new BesoinAchat();
        ArrayList<String[]> allBesoinAchatBDD = besoinAchat.select(condition);
        ArrayList<BesoinAchat> allBesoinAchat = new ArrayList<BesoinAchat>();
        for (String[] strings : allBesoinAchatBDD) {
            BesoinAchat b = new BesoinAchat();
            b.setIdBesoinAchat(Integer.parseInt(strings[0]));
            b.setIdService(Integer.parseInt(strings[1]));
            b.setDateBesoin(Timestamp.valueOf(strings[2]));
            b.setDateCloture(Timestamp.valueOf(strings[3]));
            b.setDescription(strings[5]);
            b.setStatusBesoin(Integer.parseInt(strings[4]));
            allBesoinAchat.add(b);
        }
        return allBesoinAchat;
    }

    ///////////////////////////////////////////////////////////////////////////
    public static BesoinAchat getBesoinAchatById(int idBesoinAchat) {
        BesoinAchat b = new BesoinAchat();
        String condition = "where idBesoinAchat =" + idBesoinAchat;
        ArrayList<String[]> allBesoinAchatBDD = b.select(condition);
        for (String[] strings : allBesoinAchatBDD) {
            b.setIdBesoinAchat(Integer.parseInt(strings[0]));
            b.setIdService(Integer.parseInt(strings[1]));
            b.setDateBesoin(Timestamp.valueOf(strings[2]));
            b.setDateCloture(Timestamp.valueOf(strings[3]));
            b.setDescription(strings[5]);
            b.setStatusBesoin(Integer.parseInt(strings[4]));
        }

        return b;
    }

    ///////////////////////////////////////////////////////////////////////////
    public Optional<Service> getService() {
        GenericDAO<Service> serviceDAO = new GenericDAO<>(Service.class);
        try {
            Connection c = ConnectTo.postgreS();
            serviceDAO.addToSelection("idService", idService, "");

            ArrayList<Service> service = serviceDAO.getFromDatabase(c);
            serviceDAO.freeSelection();

            c.close();

            return service.isEmpty() ? null : Optional.ofNullable(service.get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<ArticleQuantiteResponse> getArticles() {
        return allArticles();
    }
    public List<ArticleQuantiteResponse> allArticles() {
        ArrayList<ArticleBesoinAchat> articleBesoinAchats = getArticlesBesoinAchat();

        // Liste des articles
        List<ArticleQuantiteResponse> articlesQuantite = new ArrayList<>();
        for (ArticleBesoinAchat articleBesoinAchat : articleBesoinAchats) {
            Article article = Article.getArticleById(articleBesoinAchat.idArticle);
            int quantite = (int) articleBesoinAchat.getQuantite();

            ArticleQuantiteResponse articleQuantiteResponse = new ArticleQuantiteResponse();
            articleQuantiteResponse.article = article;
            articleQuantiteResponse.quantite = quantite;

            articlesQuantite.add(articleQuantiteResponse);
        }

        return articlesQuantite;
    }

    public ArrayList<ArticleBesoinAchat> getArticlesBesoinAchat() {
        return ArticleBesoinAchat.getArticleBesoinAchatByBesoinAchat(idBesoinAchat);
    }

    public String getStatusString() {
        return DemandeConfiguration.getStatusString(statusBesoin);
    }
}
