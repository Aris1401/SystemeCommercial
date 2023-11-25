package com.commercial.gestion.model;

import java.sql.Timestamp;

public class ReponseDemandeRecuProforma {
    private int idReponseDemandeRecuProforma;
    private Timestamp dateReponse;
    private String lienEmail;
    private int quantite;
    private double prixUnitaire;
    private int idDemandeRecuProforma;

    public int getIdReponseDemandeRecuProforma() {
        return idReponseDemandeRecuProforma;
    }

    public void setIdReponseDemandeRecuProforma(int idReponseDemandeRecuProforma) {
        this.idReponseDemandeRecuProforma = idReponseDemandeRecuProforma;
    }

    public Timestamp getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Timestamp dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getLienEmail() {
        return lienEmail;
    }

    public void setLienEmail(String lienEmail) {
        this.lienEmail = lienEmail;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdDemandeRecuProforma() {
        return idDemandeRecuProforma;
    }

    public void setIdDemandeRecuProforma(int idDemandeRecuProforma) {
        this.idDemandeRecuProforma = idDemandeRecuProforma;
    }

    public ReponseDemandeRecuProforma(){

    }

    public ReponseDemandeRecuProforma(int idReponseDemandeRecuProforma, Timestamp dateReponse, String lienEmail, int quantite, double prixUnitaire, int idDemandeRecuProforma) {
        this.setIdReponseDemandeRecuProforma(idReponseDemandeRecuProforma);
        this.setDateReponse(dateReponse);
        this.setLienEmail(lienEmail);
        this.setQuantite(quantite);
        this.setPrixUnitaire(prixUnitaire);
        this.setIdDemandeRecuProforma(idDemandeRecuProforma);
    }

    public DemandeRecuProforma getDemandeRecuFournisseurProforma()throws Exception{
        return DemandeRecuProforma.getById(this.getIdDemandeRecuProforma());
    }

    public static void repondre(DemandeRecuProforma demandeRecuProforma)throws Exception{
        demandeRecuProforma.valider();
    }
}
