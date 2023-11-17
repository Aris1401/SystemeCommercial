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
public class ValidationBesoinAchat
{
    int idValidationBesoinAchat;
    int idBesoinAchat; 
    int idUtilisateur;
    Timestamp dateValidation;
    String commentaire;
    int statusValidation;

    public int getIdValidationBesoinAchat() {
        return idValidationBesoinAchat;
    }

    public void setIdValidationBesoinAchat(int idValidationBesoinAchat) {
        this.idValidationBesoinAchat = idValidationBesoinAchat;
    }

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public int getStatusValidation() {
        return statusValidation;
    }

    public void setStatusValidation(int statusValidation) {
        this.statusValidation = statusValidation;
    }
    
}
