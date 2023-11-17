/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

/**
 *
 * @author BEST
 */
public class Fournisseur
{
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
  
}
