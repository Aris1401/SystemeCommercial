/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class BonDeCommande extends BDD
{
    int idBonDeCommande;
    String titre;
    Timestamp dateCreation;
    int idFournisseur;
    int idBesoinAchat;
    Timestamp dateLivraison;
    int idModeDePaiement;
    String conditionDePaiement;
    double montantTotal;
    int statusBonDeCommande;

    public int getIdBonDeCommande() {
        return idBonDeCommande;
    }

    public void setIdBonDeCommande(int idBonDeCommande) {
        this.idBonDeCommande = idBonDeCommande;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
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

    public int getIdModeDePaiement() {
        return idModeDePaiement;
    }

    public void setIdModeDePaiement(int idModeDePaiement) {
        this.idModeDePaiement = idModeDePaiement;
    }

    public String getConditionDePaiement() {
        return conditionDePaiement;
    }

    public void setConditionDePaiement(String conditionDePaiement) {
        this.conditionDePaiement = conditionDePaiement;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public int getStatusBonDeCommande() {
        return statusBonDeCommande;
    }

    public void setStatusBonDeCommande(int statusBonDeCommande) {
        this.statusBonDeCommande = statusBonDeCommande;
    }

    ////////////////////////////////////////////////////////////////////
 public boolean insertBonDeCommande(String titre,String dateDeCreation,String idFournisseur,
                                    String idBesoinAchat,String dateLivraison,String idModeDePaiement,
                                    String conditionDePaiement,String montantTotal,String status)
 {
     boolean insert=false;
    BonDeCommande bonDeCommande=new BonDeCommande();
     bonDeCommande.setTitre(titre);
     bonDeCommande.setDateCreation(Timestamp.valueOf(dateDeCreation));
     bonDeCommande.setIdFournisseur(Integer.parseInt(idFournisseur));
     bonDeCommande.setIdBesoinAchat(Integer.parseInt(idBesoinAchat));
     bonDeCommande.setDateLivraison(Timestamp.valueOf(dateLivraison));
     bonDeCommande.setIdModeDePaiement(Integer.parseInt(idModeDePaiement));
     bonDeCommande.setConditionDePaiement(conditionDePaiement);
     bonDeCommande.setMontantTotal(Double.parseDouble(montantTotal));
     bonDeCommande.dontSave("idBonDeCommande");
     bonDeCommande.setStatusBonDeCommande(Integer.parseInt(status));
     bonDeCommande.save();
     insert=true;
     return insert;
 }
////////////////////////////////////////////////////////////////////
public ArrayList<BonDeCommande> allBonDeCommande()
{
    BonDeCommande b=new BonDeCommande();
    ArrayList<String[]> allBonDeCommandeBDD=b.select();
    ArrayList<BonDeCommande> allBonDeCommande=new ArrayList<BonDeCommande>();
    for(int i=0;i<allBonDeCommandeBDD.size();i++)
    {
        BonDeCommande bonDeCommande=new BonDeCommande();
        bonDeCommande.setIdBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[0]));
        bonDeCommande.setDateCreation(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[1]));
        bonDeCommande.setIdFournisseur(Integer.parseInt(allBonDeCommandeBDD.get(i)[2]));
        bonDeCommande.setIdBesoinAchat(Integer.parseInt(allBonDeCommandeBDD.get(i)[3]));
        bonDeCommande.setDateLivraison(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[4]));
        bonDeCommande.setIdModeDePaiement(Integer.parseInt(allBonDeCommandeBDD.get(i)[5]));
        bonDeCommande.setConditionDePaiement(allBonDeCommandeBDD.get(i)[6]);
        bonDeCommande.setMontantTotal(Double.parseDouble(allBonDeCommandeBDD.get(i)[7]));
        bonDeCommande.setStatusBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[8]));
        allBonDeCommande.add(bonDeCommande);
    }
    return allBonDeCommande;
}
////////////////////////////////////////////////////////////////////
    public ArrayList<BonDeCommande> getBonDeCommandeForFournisseur(int idFournisseur)
    {
        BonDeCommande b=new BonDeCommande();
        String condition="idFournisseur ="+idFournisseur;
        ArrayList<String[]> allBonDeCommandeBDD=b.select(condition);
        ArrayList<BonDeCommande> allBonDeCommande=new ArrayList<BonDeCommande>();
        for(int i=0;i<allBonDeCommandeBDD.size();i++)
        {
            BonDeCommande bonDeCommande=new BonDeCommande();
            bonDeCommande.setIdBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[0]));
            bonDeCommande.setDateCreation(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[1]));
            bonDeCommande.setIdFournisseur(Integer.parseInt(allBonDeCommandeBDD.get(i)[2]));
            bonDeCommande.setIdBesoinAchat(Integer.parseInt(allBonDeCommandeBDD.get(i)[3]));
            bonDeCommande.setDateLivraison(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[4]));
            bonDeCommande.setIdModeDePaiement(Integer.parseInt(allBonDeCommandeBDD.get(i)[5]));
            bonDeCommande.setConditionDePaiement(allBonDeCommandeBDD.get(i)[6]);
            bonDeCommande.setMontantTotal(Double.parseDouble(allBonDeCommandeBDD.get(i)[7]));
            bonDeCommande.setStatusBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[8]));
            allBonDeCommande.add(bonDeCommande);
        }
        return allBonDeCommande;
    }
////////////////////////////////////////////////////////////////////
public ArrayList<BonDeCommande> getBonDeCommandeValide()
{
    BonDeCommande b=new BonDeCommande();
    String condition="statusBonDeCommande  = 1";
    ArrayList<String[]> allBonDeCommandeBDD=b.select(condition);
    ArrayList<BonDeCommande> allBonDeCommande=new ArrayList<BonDeCommande>();
    for(int i=0;i<allBonDeCommandeBDD.size();i++)
    {
        BonDeCommande bonDeCommande=new BonDeCommande();
        bonDeCommande.setIdBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[0]));
        bonDeCommande.setDateCreation(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[1]));
        bonDeCommande.setIdFournisseur(Integer.parseInt(allBonDeCommandeBDD.get(i)[2]));
        bonDeCommande.setIdBesoinAchat(Integer.parseInt(allBonDeCommandeBDD.get(i)[3]));
        bonDeCommande.setDateLivraison(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[4]));
        bonDeCommande.setIdModeDePaiement(Integer.parseInt(allBonDeCommandeBDD.get(i)[5]));
        bonDeCommande.setConditionDePaiement(allBonDeCommandeBDD.get(i)[6]);
        bonDeCommande.setMontantTotal(Double.parseDouble(allBonDeCommandeBDD.get(i)[7]));
        bonDeCommande.setStatusBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[8]));
        allBonDeCommande.add(bonDeCommande);
    }
    return allBonDeCommande;
}
////////////////////////////////////////////////////////////////////
public ArrayList<BonDeCommande> getBonDeCommandeNonValide()
{
    BonDeCommande b=new BonDeCommande();
    String condition="statusBonDeCommande  = 0";
    ArrayList<String[]> allBonDeCommandeBDD=b.select(condition);
    ArrayList<BonDeCommande> allBonDeCommande=new ArrayList<BonDeCommande>();
    for(int i=0;i<allBonDeCommandeBDD.size();i++)
    {
        BonDeCommande bonDeCommande=new BonDeCommande();
        bonDeCommande.setIdBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[0]));
        bonDeCommande.setDateCreation(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[1]));
        bonDeCommande.setIdFournisseur(Integer.parseInt(allBonDeCommandeBDD.get(i)[2]));
        bonDeCommande.setIdBesoinAchat(Integer.parseInt(allBonDeCommandeBDD.get(i)[3]));
        bonDeCommande.setDateLivraison(Timestamp.valueOf(allBonDeCommandeBDD.get(i)[4]));
        bonDeCommande.setIdModeDePaiement(Integer.parseInt(allBonDeCommandeBDD.get(i)[5]));
        bonDeCommande.setConditionDePaiement(allBonDeCommandeBDD.get(i)[6]);
        bonDeCommande.setMontantTotal(Double.parseDouble(allBonDeCommandeBDD.get(i)[7]));
        bonDeCommande.setStatusBonDeCommande(Integer.parseInt(allBonDeCommandeBDD.get(i)[8]));
        allBonDeCommande.add(bonDeCommande);
    }
    return allBonDeCommande;
}
////////////////////////////////////////////////////////////////////
public void validerBonDeCommande5(int idBonDeCommande,boolean ok) throws SQLException {
   if (ok=true)
   {
      BonDeCommande b=new BonDeCommande();
      b.update5(idBonDeCommande);
   }
}

//////////////////////////////////////////////////////////////////////
public void validerBonDeCommande10(int idBonDeCommande,boolean ok,boolean okok) throws SQLException {
    if (ok==true && okok==true)
    {
        BonDeCommande b=new BonDeCommande();
        b.update10(idBonDeCommande);
    }
}
//////////////////////////////////////////////////////////////////////
}
