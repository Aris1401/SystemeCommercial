package com.commercial.gestion.model;

import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProformaCoteFournisseur {
    private int idProformaCoteFournisseur;
    private int idDemandeProformaCoteFournisseur;
    private int quantite;
    private double prixUnitaire;

    public int getIdProformaCoteFournisseur() {
        return idProformaCoteFournisseur;
    }

    public void setIdProformaCoteFournisseur(int idProformaCoteFournisseur) {
        this.idProformaCoteFournisseur = idProformaCoteFournisseur;
    }

    public int getIdDemandeProformaCoteFournisseur() {
        return idDemandeProformaCoteFournisseur;
    }

    public void setIdDemandeProformaCoteFournisseur(int idDemandeProformaCoteFournisseur) {
        this.idDemandeProformaCoteFournisseur = idDemandeProformaCoteFournisseur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public ProformaCoteFournisseur() {

    }

    public ProformaCoteFournisseur(int idProformaCoteFournisseur, int idDemandeProformaCoteFournisseur, int quantite, double prixUnitaire)throws Exception{
        this.setIdProformaCoteFournisseur(idProformaCoteFournisseur);
        this.setIdDemandeProformaCoteFournisseur(idDemandeProformaCoteFournisseur);
        this.setQuantite(quantite);
        this.setPrixUnitaire(prixUnitaire);
    }

    public static ArrayList<ProformaCoteFournisseur> getAllProforma(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ProformaCoteFournisseur> allProformas = new ArrayList<>();
        String query = "select * from ProformaCoteFournisseur";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ProformaCoteFournisseur proformaCoteFournisseur = new ProformaCoteFournisseur(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getDouble(4));
                allProformas.add(proformaCoteFournisseur);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return allProformas;
    }

    public void inserer()throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO ProformaCoteFournisseur (idDemandeProformaCoteFournisseur, quantite, prixUnitaire) VALUES (?, ?, ?)";
        try {
            if(connection == null)connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.getIdDemandeProformaCoteFournisseur());
            preparedStatement.setInt(2, this.getQuantite());
            preparedStatement.setDouble(3, this.getPrixUnitaire());

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

    public static void inserer(int prixUnitaire,DemandeProformaCoteFournisseur demandeProformaCoteFournisseur)throws Exception{
        ProformaCoteFournisseur proformaCoteFournisseur = new ProformaCoteFournisseur();
        proformaCoteFournisseur.setPrixUnitaire(prixUnitaire);
        proformaCoteFournisseur.setIdDemandeProformaCoteFournisseur(demandeProformaCoteFournisseur.getIdDemandeProformaCoteFournisseur());
        proformaCoteFournisseur.setQuantite(demandeProformaCoteFournisseur.getQuantite());
        proformaCoteFournisseur.inserer();
    }
}
