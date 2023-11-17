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
public class BonDeCommande extends BDD
{
    int idBonDeCommande;
    String titre;
    Timestamp dateCreation;
    int idFournisseur;
    int idBesoinAchat;
    Timestamp dateLivraison;
    int idModeDePaiement;
    String conditionDePaiement;
    double montantTotal;  

    public int getIdBonDeCommande() {
        return idBonDeCommande;
    }

    public void setIdBonDeCommande(int idBonDeCommande) {
        this.idBonDeCommande = idBonDeCommande;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getIdModeDePaiement() {
        return idModeDePaiement;
    }

    public void setIdModeDePaiement(int idModeDePaiement) {
        this.idModeDePaiement = idModeDePaiement;
    }

    public String getConditionDePaiement() {
        return conditionDePaiement;
    }

    public void setConditionDePaiement(String conditionDePaiement) {
        this.conditionDePaiement = conditionDePaiement;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {this.montantTotal = montantTotal;}

 ////////////////////////////////////////////////////////////////////
 public boolean insertBonDeCommande(String titre,String dateDeCreation,String idFournisseur,
                                    String idBesoinAchat,String dateLivraison,String idModeDePaiement,
                                    String conditionDePaiement,String montantTotal)
 {
     boolean insert=false;
    BonDeCommande bonDeCommande=new BonDeCommande();
     bonDeCommande.setTitre(titre);
     bonDeCommande.setDateCreation(Timestamp.valueOf(dateDeCreation));
     bonDeCommande.setIdFournisseur(Integer.parseInt(idFournisseur));
     bonDeCommande.setIdBesoinAchat(Integer.parseInt(idBesoinAchat));
     bonDeCommande.setDateLivraison(Timestamp.valueOf(dateLivraison));
     bonDeCommande.setIdModeDePaiement(Integer.parseInt(idModeDePaiement));
     bonDeCommande.setConditionDePaiement(conditionDePaiement);
     bonDeCommande.setMontantTotal(Double.parseDouble(montantTotal));
     bonDeCommande.dontSave("idBonDeCommande");
     bonDeCommande.save();
     insert=true;
     return insert;
 }
    ////////////////////////////////////////////////////////////////////
}
