package com.commercial.gestion.model;

import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class BonDeCommandeEnvoie {
    private int idBonDeCommandeEnvoie;
    private int idBonDeCommande;
    private Timestamp dateEnvoie;

    public int getIdBonDeCommandeEnvoie() {
        return idBonDeCommandeEnvoie;
    }

    public void setIdBonDeCommandeEnvoie(int idBonDeCommandeEnvoie) {
        this.idBonDeCommandeEnvoie = idBonDeCommandeEnvoie;
    }

    public int getIdBonDeCommande() {
        return idBonDeCommande;
    }

    public void setIdBonDeCommande(int idBonDeCommande) {
        this.idBonDeCommande = idBonDeCommande;
    }

    public Timestamp getDateEnvoie() {
        return dateEnvoie;
    }

    public void setDateEnvoie(Timestamp dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }

    public BonDeCommandeEnvoie() {

    }

    public BonDeCommandeEnvoie(int idBonDeCommandeEnvoie, int idBonDeCommande, Timestamp dateEnvoie) {
        this.setIdBonDeCommandeEnvoie(idBonDeCommandeEnvoie);
        this.setIdBonDeCommande(idBonDeCommande);
        this.setDateEnvoie(dateEnvoie);
    }

    public BonDeCommande getBonDeCommande(){
        return BonDeCommande.obtenirBonDeCommandeAvec(this.getIdBonDeCommande());
    }

    public void inserer()throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO BonDeCommandeEnvoie (idBonDeCommande,dateEnvoie) VALUES (?, ?)";
        try {
            if(connection == null)connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.getIdBonDeCommande());
            preparedStatement.setTimestamp(2, this.getDateEnvoie());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean envoyerBonDeCommandeVersFournisseur(Utilisateur user, int bonDeCommande, int idFournisseur)throws Exception{
        boolean b = false;
        if(user.hasProfil(2)){
            if(BonDeCommande.obtenirBonDeCommandeAvec(bonDeCommande).valider(user)){
                BonDeCommandeEnvoie bonDeCommandeEnvoie = new BonDeCommandeEnvoie();
                bonDeCommandeEnvoie.setIdBonDeCommande(bonDeCommande);
                bonDeCommandeEnvoie.setDateEnvoie(Timestamp.valueOf(LocalDateTime.now()));
                bonDeCommandeEnvoie.inserer();
                b = true;
            }else{
                throw new Exception("Bon de commande non validé");
            }
        }else{
            throw new Exception("Cet utilisateur n'a pas de profil service achat !!!");
        }
        return b;
    }
}
