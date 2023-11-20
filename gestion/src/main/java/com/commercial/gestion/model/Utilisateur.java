/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class Utilisateur extends BDD
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

    ////////////////////////////////////////////////////////////////////////
    public Utilisateur connect(String email,String motDePasse)
    {
        String condition=" WHERE email ="+email +" AND motDePasse="+motDePasse;
        Utilisateur utilisateur =new Utilisateur();
        ArrayList<String[]> allBDD=utilisateur.select(condition);
        for(int i=0;i< allBDD.size();i++)
        {
            utilisateur.setIdUtilisateur(Integer.parseInt(allBDD.get(i)[0]));
            utilisateur.setNom(allBDD.get(i)[1]);
            utilisateur.setPrenom(allBDD.get(i)[2]);
            utilisateur.setEmail(allBDD.get(i)[3]);
            utilisateur.setMotDePasse(allBDD.get(i)[4]);
            utilisateur.setIdTypeUtilisateur(Integer.parseInt(allBDD.get(i)[5]));
        }
    return utilisateur;
    }
    ///////////////////////////////////////////////////////////////////////
}
