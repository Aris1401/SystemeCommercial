/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class DemandeProforma extends BDD
{
    @PrimaryKey
     int idDemandeProforma;
     int idArticleBesoinAchat;
     Timestamp dateDemande;
     int idFournisseur;
     int statusDemande;  

    public int getIdDemandeProforma() {
        return idDemandeProforma;
    }

    public void setIdDemandeProforma(int idDemandeProforma) {
        this.idDemandeProforma = idDemandeProforma;
    }

    public int getIdArticleBesoinAchat() {
        return idArticleBesoinAchat;
    }

    public void setIdArticleBesoinAchat(int idArticleBesoinAchat) {
        this.idArticleBesoinAchat = idArticleBesoinAchat;
    }

    public Timestamp getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Timestamp dateDemande) {
        this.dateDemande = dateDemande;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public int getStatusDemande() {
        return statusDemande;
    }

    public void setStatusDemande(int statusDemande) {
        this.statusDemande = statusDemande;
    }


 public boolean insertDemandeProforma(String idArticleBesoinAchat,String dateDemande,String idFournisseur,String statusDemande)
 {
     boolean insert=false;

     DemandeProforma demande=new DemandeProforma();
     demande.setIdArticleBesoinAchat(Integer.parseInt(idArticleBesoinAchat));
     demande.setDateDemande(Timestamp.valueOf(dateDemande));
     demande.setIdFournisseur(Integer.parseInt(idFournisseur));
     demande.setStatusDemande(Integer.parseInt(statusDemande));
     demande.dontSave("idDemandeProforma");
     demande.save();
     insert=true;
     return insert;

 }
    /////////////////////////////////////////////////////////
    public Fournisseur getFournisseur() {
        return Fournisseur.getFournisseurById(idFournisseur);
    }
    public ArticleBesoinAchat getArticleBesoinAchat() {
        return ArticleBesoinAchat.getArticleBesoinAchatById(idArticleBesoinAchat);
    }

    public static DemandeProforma obtenirDemande(int idDemandeProforma) {
        try {
            Connection c = ConnectTo.postgreS();

            GenericDAO<DemandeProforma> demandeProformaGenericDAO = new GenericDAO<>(DemandeProforma.class);
            demandeProformaGenericDAO.addToSelection("idDemandeProforma", idDemandeProforma, "");

            ArrayList<DemandeProforma> demandeProformas = demandeProformaGenericDAO.getFromDatabase(c);

            c.close();

            return demandeProformas.isEmpty() ? null : demandeProformas.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static DemandeProforma demandeFournisseurArticleBesoinAchat(int idFournisseur, int idArticleBesoinAchat) {
        try {
            Connection c = ConnectTo.postgreS();

            GenericDAO<DemandeProforma> demandeProformaGenericDAO = new GenericDAO<>(DemandeProforma.class);
            demandeProformaGenericDAO.addToSelection("idFournisseur", idFournisseur, "");
            demandeProformaGenericDAO.addToSelection("idArticleBesoinAchat", idArticleBesoinAchat, "and");

            ArrayList<DemandeProforma> demandeProformas = demandeProformaGenericDAO.getFromDatabase(c);

            c.close();

            return demandeProformas.isEmpty() ? null : demandeProformas.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void validerDemandeProforma() {
        DemandeProforma.validerDemandeProforma(idDemandeProforma);
    }

    public static void  validerDemandeProforma(int idDemandeProforma) {
        GenericDAO<DemandeProforma> demandeProformaGenericDAO = new GenericDAO<>(DemandeProforma.class);

        try {
            Connection c = ConnectTo.postgreS();

            demandeProformaGenericDAO.addToSetUpdate("statusDemande", DemandeConfiguration.VALIDER);
            demandeProformaGenericDAO.addToSelection("idDemandeProforma", idDemandeProforma, "");

            demandeProformaGenericDAO.updateInDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
