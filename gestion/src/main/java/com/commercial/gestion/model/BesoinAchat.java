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
public class BesoinAchat 
{
    int idBesoinAchat;
    int idService;
    Timestamp dateBesoin;
    Timestamp dateCloture;
    int statusBesoin;

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public Timestamp getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Timestamp dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public Timestamp getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Timestamp dateCloture) {
        this.dateCloture = dateCloture;
    }

    public int getStatusBesoin() {
        return statusBesoin;
    }

    public void setStatusBesoin(int statusBesoin) {
        this.statusBesoin = statusBesoin;
    }

    
}
