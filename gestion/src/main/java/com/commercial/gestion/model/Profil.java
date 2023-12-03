/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class Profil
{
    @PrimaryKey
    int idProfil;
    String nom;
    int idService;

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public static Profil obtenirProfilById(int idProfil) {
        GenericDAO<Profil> profilGenericDAO = new GenericDAO<>(Profil.class);

        Profil profil = null;
        try {
            Connection c = ConnectTo.postgreS();

            profil = profilGenericDAO.getFromDatabaseById(c, idProfil);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profil;
    }

    public static ArrayList<Profil> getAllProfils() {
        GenericDAO<Profil> profilGenericDAO = new GenericDAO<>(Profil.class);
        try {
            Connection c = ConnectTo.postgreS();

            ArrayList<Profil> profils = profilGenericDAO.getFromDatabase(c);

            c.close();

            return profils;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
