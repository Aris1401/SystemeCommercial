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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author BEST
 */
public class Proforma extends BDD {
    int idProforma;
    int idFournisseur;
    Timestamp dateObtention;
    double prixUnitaire;
    double quantite;
    int idArticle;

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Timestamp getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Timestamp dateObtention) {
        this.dateObtention = dateObtention;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<Proforma> allProforma() {
        Proforma proforma = new Proforma();
        ArrayList<String[]> allProformaBDD = proforma.select();
        ArrayList<Proforma> allProforma = new ArrayList<Proforma>();
        for (int i = 0; i < allProformaBDD.size(); i++) {
            Proforma p = new Proforma();
            p.setIdProforma(Integer.parseInt(allProformaBDD.get(i)[0]));
            p.setIdFournisseur(Integer.parseInt(allProformaBDD.get(i)[1]));
            p.setDateObtention(Timestamp.valueOf(allProformaBDD.get(i)[2]));
            p.setPrixUnitaire(Double.parseDouble(allProformaBDD.get(i)[3]));
            p.setQuantite(Double.parseDouble(allProformaBDD.get(i)[4]));
            p.setIdArticle(Integer.parseInt(allProformaBDD.get(i)[5]));

            allProforma.add(p);
        }
        return allProforma;
    }

    ////////////////////////////////////////////////////////////////////////////
    public Fournisseur getFournisseur() {
        return Fournisseur.getFournisseurById(idFournisseur);
    }

    public ArrayList<Proforma> allProformaForArticle(int idArticle) {
        Proforma proforma = new Proforma();
        String condition = "idArticle=" + idArticle;
        ArrayList<String[]> allProformaBDD = proforma.select(condition);
        ArrayList<Proforma> allProforma = new ArrayList<Proforma>();
        for (int i = 0; i < allProformaBDD.size(); i++) {
            Proforma p = new Proforma();
            p.setIdProforma(Integer.parseInt(allProformaBDD.get(i)[0]));
            p.setIdFournisseur(Integer.parseInt(allProformaBDD.get(i)[1]));
            p.setDateObtention(Timestamp.valueOf(allProformaBDD.get(i)[2]));
            p.setPrixUnitaire(Double.parseDouble(allProformaBDD.get(i)[3]));
            p.setQuantite(Double.parseDouble(allProformaBDD.get(i)[4]));
            p.setIdArticle(Integer.parseInt(allProformaBDD.get(i)[5]));

            allProforma.add(p);
        }
        return allProforma;
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean insertProforma(String idFournisseur, String dateObtention, String priUnitaire, String quantite, String idArticle) {
        boolean insert = false;

        Proforma p = new Proforma();
        p.setIdFournisseur(Integer.parseInt(idFournisseur));
        p.setDateObtention(Timestamp.valueOf(dateObtention));
        p.setPrixUnitaire(Double.parseDouble(priUnitaire));
        p.setQuantite(Double.parseDouble(quantite));
        p.setIdArticle(Integer.parseInt(idArticle));
        p.dontSave("idProforma");
        p.save();
        insert = true;
        return insert;
    }

    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Proforma> obtenirProformaBesoinArticle(int idArticleBesoinAchat) {
        ArrayList<Proforma> proformas = new ArrayList<>();
        try {
            Connection c = ConnectTo.postgreS();

            GenericDAO<ProformaArticleBesoinAchat> proformaArticleBesoinAchatGenericDAO = new GenericDAO<>(ProformaArticleBesoinAchat.class);
            proformaArticleBesoinAchatGenericDAO.addToSelection("idArticleBesoinAchat", idArticleBesoinAchat, "");

            ArrayList<ProformaArticleBesoinAchat> proformaArticleBesoinAchats = proformaArticleBesoinAchatGenericDAO.getFromDatabase(c);

            for (ProformaArticleBesoinAchat proformaArticleBesoinAchat : proformaArticleBesoinAchats) {
                GenericDAO<Proforma> proformaGenericDAO = new GenericDAO<>(Proforma.class);
                proformaGenericDAO.addToSelection("idProforma", proformaArticleBesoinAchat.getIdProforma(), "");

                ArrayList<Proforma> proformas1 = proformaGenericDAO.getFromDatabase(c);
                if (proformas1.isEmpty()) continue;

                proformas.add(proformas1.get(0));
            }

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return proformas;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Proforma> TrierPrix(int idArticle) {
        Proforma proforma = new Proforma();
        String condition = "idArticle =" + idArticle;
        ArrayList<String[]> allProformaBDD = proforma.select(condition);
        ArrayList<Proforma> allP = new ArrayList<Proforma>();

        for (int i = 0; i < allProformaBDD.size(); i++) {
            Proforma p = new Proforma();
            p.setIdProforma(Integer.parseInt(allProformaBDD.get(i)[0]));
            p.setIdFournisseur(Integer.parseInt(allProformaBDD.get(i)[1]));
            p.setDateObtention(Timestamp.valueOf(allProformaBDD.get(i)[2]));
            p.setPrixUnitaire(Double.parseDouble(allProformaBDD.get(i)[3]));
            p.setQuantite(Double.parseDouble(allProformaBDD.get(i)[4]));
            p.setIdArticle(Integer.parseInt(allProformaBDD.get(i)[5]));
            allP.add(p);
        }
        ArrayList<Proforma> vrai = new ArrayList<>();

        for (int i = allP.size(); i > 0; i--) {
            Proforma maxP = allP.get(0);
            for (int j = 0; j < allP.size(); j++) {
                Proforma p = allP.get(j);
                if (p.getPrixUnitaire() > maxP.getPrixUnitaire()) {
                    maxP = p;
                }
            }
            vrai.add(maxP);
            allP.remove(maxP);
        }
        return vrai;
    }

    //////////////////////////////////////////////////////////////////////
    public static ArrayList<Proforma> getMoinsDixAns(ArrayList<Proforma> listeProforma, int idArticleBesoinAchat) {
        ArticleBesoinAchat articleBesoinAchat = ArticleBesoinAchat.getArticleBesoinAchatById(idArticleBesoinAchat);

        // Sort proforma par prix unitaire
        listeProforma.sort(Comparator.comparingDouble(Proforma::getPrixUnitaire));

        ArrayList<Proforma> moisDixAns = new ArrayList<>();
        int moinsDixAnsIndex = 0;

        // Tant que la quantité dans ArticleBesoinAchat n'est pas suffisante
        while (moinsDixAnsIndex < listeProforma.size() && articleBesoinAchat.getQuantite() > 0) {
            Proforma proformaActuel = listeProforma.get(moinsDixAnsIndex);

            // Calculer la quantité à ajouter (minimum entre la quantité dans le Proforma actuel et la quantité dans ArticleBesoinAchat)
            double quantiteAjouter = Math.min(proformaActuel.getQuantite(), articleBesoinAchat.getQuantite());

            // Créer une nouvelle instance de Proforma avec la quantité correcte et ajouter à moisDixAns
            Proforma proformaAjouter = new Proforma();
            proformaAjouter.setIdProforma(proformaActuel.getIdProforma());
            proformaAjouter.setIdFournisseur(proformaActuel.getIdFournisseur());
            proformaAjouter.setDateObtention(proformaActuel.getDateObtention());
            proformaAjouter.setPrixUnitaire(proformaActuel.getPrixUnitaire());
            proformaAjouter.setQuantite(quantiteAjouter);
            proformaAjouter.setIdArticle(proformaActuel.getIdArticle());
            moisDixAns.add(proformaAjouter);

            // Mettre à jour la quantité dans ArticleBesoinAchat
            articleBesoinAchat.setQuantite(articleBesoinAchat.getQuantite() - quantiteAjouter);

            moinsDixAnsIndex++;
        }

        return moisDixAns;
    }
}
