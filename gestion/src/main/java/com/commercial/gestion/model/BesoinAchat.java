/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.configuration.ProformaConfiguration;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.response.ArticleQuantiteResponse;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.*;

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

    public double getEstimationMontantTotal() {
        ArrayList<ArticleBesoinAchat> articleBesoinAchats = getArticlesBesoinAchat();

        double estimation = 0;

        for (ArticleBesoinAchat articleBesoinAchat : articleBesoinAchats) {
            estimation += articleBesoinAchat.getEstimationPrix() * articleBesoinAchat.getQuantite();
        }

        return estimation;
    }

    public boolean cloreBesoin() {
        return updateStatusBesoin(DemandeConfiguration.CLOT, new Timestamp(System.currentTimeMillis()));
    }

    public boolean validerBesoin(int idUtilisateur) {
        ValidationBesoinAchat validationBesoinAchat = new ValidationBesoinAchat();
        validationBesoinAchat.setDateValidation(new Timestamp(System.currentTimeMillis()));
        validationBesoinAchat.setCommentaire("");
        validationBesoinAchat.setIdUtilisateur(idUtilisateur);
        validationBesoinAchat.setIdBesoinAchat(idBesoinAchat);
        validationBesoinAchat.setStatusValidation(DemandeConfiguration.VALIDER);
        validationBesoinAchat.dontSave("idValidationBonDeCommande");
        validationBesoinAchat.save();

        // TODO: Checker si l'utilisateur possede le profil necessaire
        return updateStatusBesoin(DemandeConfiguration.VALIDER, null);
    }

    public boolean updateStatusBesoin(int status, Timestamp now) {
        GenericDAO<BesoinAchat> besoinAchatGenericDAO = new GenericDAO<>(BesoinAchat.class);

        try {
            Connection c = ConnectTo.postgreS();

            besoinAchatGenericDAO.addToSetUpdate("statusBesoin", status);
            besoinAchatGenericDAO.addToSelection("idBesoinAchat", idBesoinAchat, "");
            if (now != null) besoinAchatGenericDAO.addToSetUpdate("dateCloture", now.toString());
            besoinAchatGenericDAO.updateInDatabase(c);

            c.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public ArrayList<BonDeCommande> genererBonDeCommandes() throws Exception {
        // Obtenir tout les articles correspondants
        ArrayList<ArticleBesoinAchat> articleBesoinAchats = getArticlesBesoinAchat();

        // Regrouper les articles de meme fournisseurs par leurs pro-formas
        Map<Integer, ArrayList<ArticleBesoinAchat>> articlesGroupees = new HashMap<>();
        for (ArticleBesoinAchat articleBesoinAchat : articleBesoinAchats) {
            ArrayList<Proforma> proformas = articleBesoinAchat.obtenirProformas();

            // Checker des proformas sont inssufisants
            if (proformas.size() < ProformaConfiguration.MIN_PROFORMA_COUNT) {
                throw new Exception("Proformas insuffisants pour continuer. Minimum pour chaque article: " + ProformaConfiguration.MIN_PROFORMA_COUNT);
            }

            // Obtenir le ou les -10ans
            ArrayList<Proforma> moinsDixAns = Proforma.getMoinsDixAns(proformas, articleBesoinAchat.getIdArticleBesoinAchat());

            for (Proforma moinsDixAnsItem : moinsDixAns) {
                ArticleBesoinAchat newArticle = new ArticleBesoinAchat(articleBesoinAchat);

                // Remplacer la quantite
                newArticle.setQuantite(moinsDixAnsItem.getQuantite());

                // Ajouter au dictionnaire
                ArrayList<ArticleBesoinAchat> listeArticleBesoinAchat = new ArrayList<>();
                if (articlesGroupees.containsKey(moinsDixAnsItem.getIdFournisseur())) listeArticleBesoinAchat = articlesGroupees.get(moinsDixAnsItem.getIdFournisseur());
                listeArticleBesoinAchat.add(newArticle);
                articlesGroupees.put(moinsDixAnsItem.getIdFournisseur(), listeArticleBesoinAchat);
            }
        }

        // Creation des bons de commande
        ArrayList<BonDeCommande> bonDeCommandes = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<ArticleBesoinAchat>> entry : articlesGroupees.entrySet()) {
            bonDeCommandes.add(BonDeCommande.createBonDeCommande(entry.getKey(), entry.getValue()));
        }

        return bonDeCommandes;
    }
}
