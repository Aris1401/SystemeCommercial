package com.commercial.gestion.model;

import java.sql.Timestamp;

public class Facture {
    private int idFacture;
    private int idBonDeCommandeEnvoie;
    private int idFournisseur;
    private Timestamp dateFacture;
    private String refFacture;
    private String pdfFacture;
    private Timestamp dateAjout;
    private double montantTotal;

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
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

    public Timestamp getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Timestamp dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getRefFacture() {
        return refFacture;
    }

    public void setRefFacture(String refFacture) {
        this.refFacture = refFacture;
    }

    public String getPdfFacture() {
        return pdfFacture;
    }

    public void setPdfFacture(String pdfFacture) {
        this.pdfFacture = pdfFacture;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Facture(){

    }
    public Facture(int idFacture, int idBonDeCommandeEnvoie, int idFournisseur, Timestamp dateFacture, String refFacture, String pdfFacture, Timestamp dateAjout, double montantTotal) {
        this.setIdFacture(idFacture);
        this.setIdBonDeCommandeEnvoie(idBonDeCommandeEnvoie);
        this.setIdFournisseur(idFournisseur);
        this.setDateFacture(dateFacture);
        this.setRefFacture(refFacture);
        this.setPdfFacture(pdfFacture);
        this.setDateAjout(dateAjout);
        this.setMontantTotal(montantTotal);
    }

    public boolean ajouterFacture(){
        return false;
    }
}
