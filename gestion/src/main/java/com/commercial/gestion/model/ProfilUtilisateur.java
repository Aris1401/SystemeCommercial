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
public class ProfilUtilisateur 
{
    int idProfilUtilisateur;
    int idUtilisateur;
    int idProfil;
    Timestamp dateAjout;  

    public int getIdProfilUtilisateur() {
        return idProfilUtilisateur;
    }

    public void setIdProfilUtilisateur(int idProfilUtilisateur) {
        this.idProfilUtilisateur = idProfilUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }
    
}
