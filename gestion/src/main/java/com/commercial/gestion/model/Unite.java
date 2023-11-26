/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
@Entity
public class Unite 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    int idUnite;
    String nom;

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public static Unite getById(int idUnite) {
        GenericDAO<Unite> uniteGenericDAO = new GenericDAO<>(Unite.class);

        Unite unite = null;
        try {
            Connection c = ConnectTo.postgreS();

            unite = uniteGenericDAO.getFromDatabaseById(c, idUnite);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return unite;
    }
}
