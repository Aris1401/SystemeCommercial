/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import java.sql.Timestamp;

/**
 *
 * @author BEST
 */
public class ValidationBonDeCommande 
{
   int idValidationBonDeCommande;
   Timestamp dateValidation;
   String commentaire;
   int idUtilisateur;
   int statusValidation;

    public int getIdValidationBonDeCommande() {
        return idValidationBonDeCommande;
    }

    public void setIdValidationBonDeCommande(int idValidationBonDeCommande) {
        this.idValidationBonDeCommande = idValidationBonDeCommande;
    }

    public Timestamp getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Timestamp dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getStatusValidation() {
        return statusValidation;
    }

    public void setStatusValidation(int statusValidation) {
        this.statusValidation = statusValidation;
    }
   
   
}
