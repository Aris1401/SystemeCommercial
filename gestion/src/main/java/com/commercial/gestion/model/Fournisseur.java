/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author BEST
 */
public class Fournisseur extends BDD {
    int idFournisseur;
    String nom;
    String contact;
    String nomResponsable;
    String contactResponsable;
    String email;
    String addresse;
    String localisation;
    String descriptionFournisseur;
    int idTypeProduit;

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getContactResponsable() {
        return contactResponsable;
    }

    public void setContactResponsable(String contactResponsable) {
        this.contactResponsable = contactResponsable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescriptionFournisseur() {
        return descriptionFournisseur;
    }

    public void setDescriptionFournisseur(String descriptionFournisseur) {
        this.descriptionFournisseur = descriptionFournisseur;
    }

    public int getIdTypeProduit() {
        return idTypeProduit;
    }

    public void setIdTypeProduit(int idTypeProduit) {
        this.idTypeProduit = idTypeProduit;
    }

    //////////////////////////////////////////////////////////////////////////
    public boolean insertFournisseur(String nom, String contact, String nomResponsable, String contactResponsable,
                                     String email, String addresse, String localisation, String descriptionFournisseur,
                                     String idTypeProduit) {
        boolean insert = false;
        Fournisseur f = new Fournisseur();
        f.setNom(nom);
        f.setContact(contact);
        f.setNomResponsable(nomResponsable);
        f.setContactResponsable(contactResponsable);
        f.setEmail(email);
        f.setAddresse(addresse);
        f.setLocalisation(localisation);
        f.setDescriptionFournisseur(descriptionFournisseur);
        f.setIdTypeProduit(Integer.parseInt(idTypeProduit));
        f.dontSave("idFournisseur");
        f.save();
        insert = true;

        return insert;
    }

    //////////////////////////////////////////////////////////////////////////
    public static ArrayList<Fournisseur> allFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        ArrayList<String[]> allFournisseurBDD = fournisseur.select();
        ArrayList<Fournisseur> allFournisseur = new ArrayList<Fournisseur>();
        for (int i = 0; i < allFournisseurBDD.size(); i++) {
            Fournisseur f = new Fournisseur();
            f.setIdFournisseur(Integer.parseInt(allFournisseurBDD.get(i)[0]));
            f.setNom(allFournisseurBDD.get(i)[1]);
            f.setContact(allFournisseurBDD.get(i)[2]);
            f.setNomResponsable(allFournisseurBDD.get(i)[3]);
            f.setContactResponsable(allFournisseurBDD.get(i)[4]);
            f.setEmail(allFournisseurBDD.get(i)[5]);
            f.setAddresse(allFournisseurBDD.get(i)[6]);
            f.setLocalisation(allFournisseurBDD.get(i)[7]);
            f.setDescriptionFournisseur(allFournisseurBDD.get(i)[8]);
            f.setIdTypeProduit(Integer.parseInt(allFournisseurBDD.get(i)[9]));
            allFournisseur.add(f);
        }
        return allFournisseur;
    }

    //////////////////////////////////////////////////////////////////////////
    public TypeProduit getTypeProduit() {
        GenericDAO<TypeProduit> typeProduitGenericDAO = new GenericDAO<>(TypeProduit.class);

        try {
            Connection c = ConnectTo.postgreS();

            TypeProduit typeProduit = typeProduitGenericDAO.getFromDatabaseById(c, idTypeProduit);

            c.close();

            return typeProduit;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Fournisseur getFournisseurById(int idFournisseur) {
        if (idFournisseur == 0) return Fournisseur.getCompany();

        Fournisseur f = new Fournisseur();
        String condition = "where idFournisseur = " + idFournisseur;
        ArrayList<String[]> allFournisseurBDD = f.select(condition);
        for (int i = 0; i < allFournisseurBDD.size(); i++) {
            f.setIdFournisseur(Integer.parseInt(allFournisseurBDD.get(i)[0]));
            f.setNom(allFournisseurBDD.get(i)[1]);
            f.setContact(allFournisseurBDD.get(i)[2]);
            f.setNomResponsable(allFournisseurBDD.get(i)[3]);
            f.setContactResponsable(allFournisseurBDD.get(i)[4]);
            f.setEmail(allFournisseurBDD.get(i)[5]);
            f.setAddresse(allFournisseurBDD.get(i)[6]);
            f.setLocalisation(allFournisseurBDD.get(i)[7]);
            f.setDescriptionFournisseur(allFournisseurBDD.get(i)[8]);
            f.setIdTypeProduit(Integer.parseInt(allFournisseurBDD.get(i)[9]));

        }
        return f;
    }

    public static Fournisseur getFournisseurByEmail(String email) {
        if (email.equals(ConfigurationValues.getConfigurationValue("company-email"))) return getCompany();

        Fournisseur f = new Fournisseur();
        String condition = "where email = '" + email + "'";
        ArrayList<String[]> allFournisseurBDD = f.select(condition);
        for (int i = 0; i < allFournisseurBDD.size(); i++) {
            f.setIdFournisseur(Integer.parseInt(allFournisseurBDD.get(i)[0]));
            f.setNom(allFournisseurBDD.get(i)[1]);
            f.setContact(allFournisseurBDD.get(i)[2]);
            f.setNomResponsable(allFournisseurBDD.get(i)[3]);
            f.setContactResponsable(allFournisseurBDD.get(i)[4]);
            f.setEmail(allFournisseurBDD.get(i)[5]);
            f.setAddresse(allFournisseurBDD.get(i)[6]);
            f.setLocalisation(allFournisseurBDD.get(i)[7]);
            f.setDescriptionFournisseur(allFournisseurBDD.get(i)[8]);
            f.setIdTypeProduit(Integer.parseInt(allFournisseurBDD.get(i)[9]));

        }
        return f;
    }

    public static Fournisseur getCompany() {
        Fournisseur fournisseur = new Fournisseur();
        String idFournisseur = ConfigurationValues.getConfigurationValue("company-id");
        String email = ConfigurationValues.getConfigurationValue("company-email");
        String name = ConfigurationValues.getConfigurationValue("company-name");

        fournisseur.setEmail(email);
        fournisseur.setNom(name);
        fournisseur.setIdFournisseur(Integer.parseInt(idFournisseur));

        return fournisseur;
    }
}
