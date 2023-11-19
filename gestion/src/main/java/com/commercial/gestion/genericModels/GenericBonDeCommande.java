package com.commercial.gestion.genericModels;

import com.commercial.gestion.aris.bdd.annotations.ExcludeFromInsertion;
import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.annotations.Table;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;

import java.sql.Timestamp;

@Table(tableName = "BonDeCommande")
public class GenericBonDeCommande extends GenericDAO<GenericBonDeCommande> {
    @PrimaryKey
    @ExcludeFromInsertion
    int idBonDeCommande;
    String titre;
    Timestamp dateCreation;
    int idFournisseur;
    int idBesoinAchat;
    Timestamp dateLivraison;
    int idModeDePaiement;
    String conditionDePaiement;
    double montantTotal;
    int statusBonDeCommande;
    public GenericBonDeCommande() {

    }

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

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public int getStatusBonDeCommande() {
        return statusBonDeCommande;
    }

    public void setStatusBonDeCommande(int statusBonDeCommande) {
        this.statusBonDeCommande = statusBonDeCommande;
    }
}
