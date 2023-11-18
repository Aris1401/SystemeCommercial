/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.sql.Timestamp;

/**
 *
 * @author BEST
 */
public class DemandeProforma extends BDD
{
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
}
