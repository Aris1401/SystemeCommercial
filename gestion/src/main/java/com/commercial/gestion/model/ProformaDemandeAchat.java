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
public class ProformaDemandeAchat 
{
    int idProformaDemandeAchat;
    int idProforma;
    int idBesoinAchat;
    Timestamp dateLivraison;

    public int getIdProformaDemandeAchat() {
        return idProformaDemandeAchat;
    }

    public void setIdProformaDemandeAchat(int idProformaDemandeAchat) {
        this.idProformaDemandeAchat = idProformaDemandeAchat;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
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
    
}
