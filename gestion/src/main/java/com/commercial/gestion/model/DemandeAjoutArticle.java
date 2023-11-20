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

import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class DemandeAjoutArticle extends BDD
{
    int idDemandeAjoutArticle;
    String nomArticle;
    Timestamp dateLivraison;
    int statusDemande;


    public int getIdDemandeAjoutArticle() {
        return idDemandeAjoutArticle;
    }

    public void setIdDemandeAjoutArticle(int idDemandeAjoutArticle) {
        this.idDemandeAjoutArticle = idDemandeAjoutArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getStatusDemande() {
        return statusDemande;
    }

    public void setStatusDemande(int statusDemande) {
        this.statusDemande = statusDemande;
    }

    ///////////////////////////////////////////////////////////
    public static ArrayList<DemandeAjoutArticle> allDemandeAjoutArticle()
    {
        DemandeAjoutArticle demandeAjoutArticle=new DemandeAjoutArticle();
        String condition = " where statusDemande = " + DemandeConfiguration.EN_COURS;
        ArrayList<String[]> allDemandeAjoutArticleBDD=demandeAjoutArticle.select(condition);
        ArrayList<DemandeAjoutArticle> allDemandeAjoutArticle=new ArrayList<DemandeAjoutArticle>();
        for(int i=0;i< allDemandeAjoutArticleBDD.size();i++)
        {
            DemandeAjoutArticle d=new DemandeAjoutArticle();
            d.setIdDemandeAjoutArticle(Integer.parseInt(allDemandeAjoutArticleBDD.get(i)[0]));
            d.setNomArticle(allDemandeAjoutArticleBDD.get(i)[1]);
            d.setDateLivraison(Timestamp.valueOf(allDemandeAjoutArticleBDD.get(i)[2]));
            d.setStatusDemande(Integer.parseInt(allDemandeAjoutArticleBDD.get(i)[3]));

            allDemandeAjoutArticle.add(d);
        }
        return allDemandeAjoutArticle;

    }
///////////////////////////////////////////////////////////
    public boolean insertDemandeAjoutArticle(String nomArticle,String dateLivraison)
    {
        boolean insert=false;
        DemandeAjoutArticle d=new DemandeAjoutArticle();
        d.setNomArticle(nomArticle);
        d.setDateLivraison(Timestamp.valueOf(dateLivraison));
        d.dontSave("idDemandeAjoutArticle");
        d.save();
        insert=true;
        return insert;
    }
///////////////////////////////////////////////////////////
    public void valider() {
        GenericDAO<DemandeAjoutArticle> demandeAjoutArticleGenericDAO = new GenericDAO<>(DemandeAjoutArticle.class);

        try {
            Connection c = ConnectTo.postgreS();

            demandeAjoutArticleGenericDAO.addToSetUpdate("statusDemande", DemandeConfiguration.VALIDER);
            demandeAjoutArticleGenericDAO.addToSelection("idDemandeAjoutArticle", idDemandeAjoutArticle, "");

            demandeAjoutArticleGenericDAO.updateInDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DemandeAjoutArticle getById(int idDemandeAjoutArticle) {
        GenericDAO<DemandeAjoutArticle> demandeAjoutArticleGenericDAO = new GenericDAO<>(DemandeAjoutArticle.class);

        try {
            Connection c = ConnectTo.postgreS();

            demandeAjoutArticleGenericDAO.addToSelection("idDemandeAjoutArticle", idDemandeAjoutArticle, "");
            ArrayList<DemandeAjoutArticle> demandeAjoutArticles = demandeAjoutArticleGenericDAO.getFromDatabase(c);

            c.close();

            return demandeAjoutArticles.isEmpty() ? null : demandeAjoutArticles.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
