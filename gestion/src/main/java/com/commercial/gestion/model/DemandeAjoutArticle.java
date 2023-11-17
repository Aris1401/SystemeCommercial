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
public class DemandeAjoutArticle
{
    int idDemandeAjoutArticle;
    String nomArticle;
    Timestamp dateLivraison;

    public int getIdDemandeAjoutArticle() {
        return idDemandeAjoutArticle;
    }

    public void setIdDemandeAjoutArticle(int idDemandeAjoutArticle) {
        this.idDemandeAjoutArticle = idDemandeAjoutArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
    
    
}
