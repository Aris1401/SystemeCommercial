/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;


import com.commercial.gestion.BDDIante.BDD;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class Utilisateur extends BDD implements Serializable
{
    int idUtilisateur;
    String nom;
    String prenom;
    String email;
    String motDePasse;
    int idTypeUtilisateur;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getIdTypeUtilisateur() {
        return idTypeUtilisateur;
    }

    public void setIdTypeUtilisateur(int idTypeUtilisateur) {
        this.idTypeUtilisateur = idTypeUtilisateur;
    }

    public ArrayList<ProfilUtilisateur> getProfilUtilisateur() {
        Utilisateur user = Utilisateur.connect(email, motDePasse);

        return ProfilUtilisateur.getProfilUtilisateur(user);
    }

    public ArrayList<Profil> getProfils() {
        ArrayList<Profil> profils = new ArrayList<>();

        ArrayList<ProfilUtilisateur> profilUtilisateurs = getProfilUtilisateur();
        for (ProfilUtilisateur profilUtilisateur : profilUtilisateurs) {
            profils.add(Profil.obtenirProfilById(profilUtilisateur.getIdProfil()));
        }

        return profils;
    }

    public boolean hasProfil(int idProfil) {
        ArrayList<Profil> profils = getProfils();

        for (Profil profil : profils) {
            if (profil.getIdProfil() == idProfil) return true;
        }

        return false;
    }

    ////////////////////////////////////////////////////////////////////////
    public static Utilisateur connect(String email,String motDePasse)
    {
        String condition=" WHERE email ='"+email +"' AND motDePasse= '"+motDePasse+"'";
        Utilisateur utilisateur =new Utilisateur();
        Utilisateur returnedUtilisateur = null;
        ArrayList<String[]> allBDD=utilisateur.select(condition);
        for(int i=0;i< allBDD.size();i++)
        {
            if (returnedUtilisateur == null) returnedUtilisateur = new Utilisateur();
            returnedUtilisateur.setIdUtilisateur(Integer.parseInt(allBDD.get(i)[0]));
            returnedUtilisateur.setNom(allBDD.get(i)[1]);
            returnedUtilisateur.setPrenom(allBDD.get(i)[2]);
            returnedUtilisateur.setEmail(allBDD.get(i)[3]);
            returnedUtilisateur.setMotDePasse(allBDD.get(i)[4]);
            returnedUtilisateur.setIdTypeUtilisateur(Integer.parseInt(allBDD.get(i)[5]));
        }
    return returnedUtilisateur;
    }
    //////////////////////////////////////////////////////////////////////
}
