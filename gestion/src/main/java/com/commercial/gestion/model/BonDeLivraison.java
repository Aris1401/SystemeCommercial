package com.commercial.gestion.model;

import java.sql.Timestamp;

public class BonDeLivraison {
    private int idBonDeLivraison;
    private int idFournisseur;
    private Timestamp dateEnregistrement;
    private Timestamp dateLivraison;
    private String referenceLivraison;
    private String bonDeLivraisonPath;
    private int idBonDeCommandeEnvoie;

    public int getIdBonDeLivraison() {
        return idBonDeLivraison;
    }

    public void setIdBonDeLivraison(int idBonDeLivraison) {
        this.idBonDeLivraison = idBonDeLivraison;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Timestamp getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Timestamp dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getReferenceLivraison() {
        return referenceLivraison;
    }

    public void setReferenceLivraison(String referenceLivraison) {
        this.referenceLivraison = referenceLivraison;
    }

    public String getBonDeLivraisonPath() {
        return bonDeLivraisonPath;
    }

    public void setBonDeLivraisonPath(String bonDeLivraisonPath) {
        this.bonDeLivraisonPath = bonDeLivraisonPath;
    }

    public int getIdBonDeCommandeEnvoie() {
        return idBonDeCommandeEnvoie;
    }

    public void setIdBonDeCommandeEnvoie(int idBonDeCommandeEnvoie) {
        this.idBonDeCommandeEnvoie = idBonDeCommandeEnvoie;
    }

    public BonDeLivraison() {

    }

    public BonDeLivraison(int idBonDeLivraison, int idFournisseur, Timestamp dateEnregistrement, Timestamp dateLivraison, String referenceLivraison, String bonDeLivraisonPath, int idBonDeCommandeEnvoie) {
        this.setIdBonDeLivraison(idBonDeLivraison);
        this.setIdFournisseur(idFournisseur);
        this.setDateEnregistrement(dateEnregistrement);
        this.setDateLivraison(dateLivraison);
        this.setReferenceLivraison(referenceLivraison);
        this.setBonDeLivraisonPath(bonDeLivraisonPath);
        this.setIdBonDeCommandeEnvoie(idBonDeCommandeEnvoie);
    }

    public boolean ajoutBonDeLivraison(){
        return false;
    }
}
