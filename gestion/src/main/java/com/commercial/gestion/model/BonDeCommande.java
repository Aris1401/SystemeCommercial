/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;
import com.commercial.gestion.BDDIante.annotations.NotInTable;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.genericModels.GenericBonDeCommande;

import java.sql.Connection;
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

    @NotInTable
    ArrayList<ArticleBonDeCommande> articleBonDeCommandes;

    public BonDeCommande(GenericBonDeCommande genericBonDeCommande) {
        this.idBonDeCommande = genericBonDeCommande.getIdBonDeCommande();
        this.titre = genericBonDeCommande.getTitre();
        this.dateCreation = genericBonDeCommande.getDateCreation();
        this.idFournisseur = genericBonDeCommande.getIdFournisseur();
        this.idBesoinAchat = genericBonDeCommande.getIdBesoinAchat();
        this.dateLivraison = genericBonDeCommande.getDateLivraison();
        this.idModeDePaiement = genericBonDeCommande.getIdModeDePaiement() == null ? 0 : Integer.parseInt(genericBonDeCommande.getIdModeDePaiement());
        this.conditionDePaiement = genericBonDeCommande.getConditionDePaiement();
        this.montantTotal = genericBonDeCommande.getMontantTotal();
        this.statusBonDeCommande = genericBonDeCommande.getStatusBonDeCommande();
        this.articleBonDeCommandes = new ArrayList<>();  // Initialize the list if needed
    }

    public BonDeCommande() {}
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

    public ArrayList<ArticleBonDeCommande> getArticleBonDeCommandes() {
        GenericDAO<ArticleBonDeCommande> articleBonDeCommandeGenericDAO = new GenericDAO<>(ArticleBonDeCommande.class);

        ArrayList<ArticleBonDeCommande> articleBonDeCommandes1 = new ArrayList<>();
        try {
            Connection c = ConnectTo.postgreS();

            articleBonDeCommandeGenericDAO.addToSelection("idBonDeCommande", idBonDeCommande, "");
            articleBonDeCommandes1 = articleBonDeCommandeGenericDAO.getFromDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return articleBonDeCommandes1;
    }

    public double getTotalPrixHT() {
        ArrayList<ArticleBonDeCommande> articleBonDeCommandes1 = getArticleBonDeCommandes();

        double total = 0;

        for (ArticleBonDeCommande articleBonDeCommande : articleBonDeCommandes1) {
            total += articleBonDeCommande.getTotalPrixHorsTaxe();
        }

        return total;
    }

    public double getTotalPrixTVA() {
        ArrayList<ArticleBonDeCommande> articleBonDeCommandes1 = getArticleBonDeCommandes();

        double total = 0;

        for (ArticleBonDeCommande articleBonDeCommande : articleBonDeCommandes1) {
            total += articleBonDeCommande.getTVA();
        }

        return total;
    }

    public double getTotalPrixTTC() {
        ArrayList<ArticleBonDeCommande> articleBonDeCommandes1 = getArticleBonDeCommandes();

        double total = 0;

        for (ArticleBonDeCommande articleBonDeCommande : articleBonDeCommandes1) {
            total += articleBonDeCommande.getTotalPrixTTC();
        }

        return total;
    }

    public BesoinAchat getBesoinAchat() {
        return BesoinAchat.getBesoinAchatById(idBesoinAchat);
    }

    public Fournisseur getFournisseur() {
        return Fournisseur.getFournisseurById(idFournisseur);
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
    public ArrayList<BonDeCommande> obtenirBonDeCommandeForFournisseur(int idFournisseur)
    {
        BonDeCommande b=new BonDeCommande();
        String condition="where idFournisseur ="+idFournisseur;
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
public ArrayList<BonDeCommande> obtenirBonDeCommandeValide()
{
    BonDeCommande b=new BonDeCommande();
    String condition="where statusBonDeCommande  = 1";
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
    String condition="  where statusBonDeCommande  = 0";
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
    public static BonDeCommande createBonDeCommande(int idFournisseur, int idBesoinAchat, ArrayList<ArticleBesoinAchat> articleBesoinAchats) {
        GenericBonDeCommande genericBonDeCommande = new GenericBonDeCommande();
        genericBonDeCommande.setIdFournisseur(idFournisseur);
        genericBonDeCommande.setIdBesoinAchat(idBesoinAchat);
        genericBonDeCommande.setDateCreation(new Timestamp(System.currentTimeMillis()));

        BonDeCommande bonDeCommande = null;
        Connection c = null;
        try {
            c = ConnectTo.postgreS();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            c.setAutoCommit(false);

            int idBonDeCommande = genericBonDeCommande.saveInDatabse(c);
            if (idBonDeCommande == -1) {
                c.rollback();
                return null;
            }

            c.commit();

            bonDeCommande = new BonDeCommande(genericBonDeCommande);

            if (bonDeCommande.articleBonDeCommandes == null) bonDeCommande.articleBonDeCommandes = new ArrayList<>();
            // Insertion des articles bon de commandes
            for (ArticleBesoinAchat articleBesoinAchat : articleBesoinAchats) {
                ArticleBonDeCommande articleBonDeCommande = ArticleBonDeCommande.createArticleBonDeCommande(idBonDeCommande, articleBesoinAchat);
                bonDeCommande.articleBonDeCommandes.add(articleBonDeCommande);

                articleBonDeCommande.dontSave("idArticleBonDeCommande");
                articleBonDeCommande.save();
            }

            c.commit();
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return bonDeCommande;
    }
}
