/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
@Entity
public class ModeDePaiement 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idModeDePaiement;
    String nom;

    public int getIdModeDePaiement() {
        return idModeDePaiement;
    }

    public void setIdModeDePaiement(int idModeDePaiement) {
        this.idModeDePaiement = idModeDePaiement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static ArrayList<ModeDePaiement> allModesDePaiement() {
        GenericDAO<ModeDePaiement> modeDePaiementGenericDAO = new GenericDAO<>(ModeDePaiement.class);

        ArrayList<ModeDePaiement> modeDePaiements = new ArrayList<>();
        try {
            Connection c = ConnectTo.postgreS();

            modeDePaiements = modeDePaiementGenericDAO.getFromDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return modeDePaiements;
    }

    public static ModeDePaiement obtenirParId(int idModeDePaiement) {
        GenericDAO<ModeDePaiement> modeDePaiementGenericDAO = new GenericDAO<>(ModeDePaiement.class);

        ArrayList<ModeDePaiement> modeDePaiements = new ArrayList<>();
        try {
            Connection c = ConnectTo.postgreS();

            modeDePaiementGenericDAO.addToSelection("idModeDePaiement", idModeDePaiement, "");
            modeDePaiements = modeDePaiementGenericDAO.getFromDatabase(c);

            c.close();

            return modeDePaiements.isEmpty() ? null : modeDePaiements.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
