package com.commercial.gestion.model;

import java.sql.Timestamp;

public class BonDeReception {
    private int idBonDeReception;
    private int idBonDeCommandeEnvoie;
    private int idFournisseur;
    private Timestamp dateEnregistrement;
    private Timestamp dateReception;
    private String refBonDeReception;
    private String bonDeReceptionPath;

    public int getIdBonDeReception() {
        return idBonDeReception;
    }

    public void setIdBonDeReception(int idBonDeReception) {
        this.idBonDeReception = idBonDeReception;
    }

    public int getIdBonDeCommandeEnvoie() {
        return idBonDeCommandeEnvoie;
    }

    public void setIdBonDeCommandeEnvoie(int idBonDeCommandeEnvoie) {
        this.idBonDeCommandeEnvoie = idBonDeCommandeEnvoie;
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

    public Timestamp getDateReception() {
        return dateReception;
    }

    public void setDateReception(Timestamp dateReception) {
        this.dateReception = dateReception;
    }

    public String getRefBonDeReception() {
        return refBonDeReception;
    }

    public void setRefBonDeReception(String refBonDeReception) {
        this.refBonDeReception = refBonDeReception;
    }

    public String getBonDeReceptionPath() {
        return bonDeReceptionPath;
    }

    public void setBonDeReceptionPath(String bonDeReceptionPath) {
        this.bonDeReceptionPath = bonDeReceptionPath;
    }

    public BonDeReception(){

    }

    public BonDeReception(int idBonDeReception, int idBonDeCommandeEnvoie, int idFournisseur, Timestamp dateEnregistrement, Timestamp dateReception, String refBonDeReception, String bonDeReceptionPath) {
        this.setIdBonDeReception(idBonDeReception);
        this.setIdBonDeCommandeEnvoie(idBonDeCommandeEnvoie);
        this.setIdFournisseur(idFournisseur);
        this.setDateEnregistrement(dateEnregistrement);
        this.setDateReception(dateReception);
        this.setRefBonDeReception(refBonDeReception);
        this.setBonDeReceptionPath(bonDeReceptionPath);
    }

    public boolean ajouterBonDeReception(){
        return false;
    }
}
