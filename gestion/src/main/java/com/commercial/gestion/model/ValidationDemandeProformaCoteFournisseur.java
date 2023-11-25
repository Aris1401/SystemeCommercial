package com.commercial.gestion.model;

import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValidationDemandeProformaCoteFournisseur {
    private int idValidationDemandeProformaCoteFournisseur;
    private Timestamp dateValidation;
    private int idDemandeProformaCoteFournisseur;

    public int getIdValidationDemandeProformaCoteFournisseur() {
        return idValidationDemandeProformaCoteFournisseur;
    }

    public void setIdValidationDemandeProformaCoteFournisseur(int idValidationDemandeProformaCoteFournisseur) {
        this.idValidationDemandeProformaCoteFournisseur = idValidationDemandeProformaCoteFournisseur;
    }

    public Timestamp getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Timestamp dateValidation) {
        this.dateValidation = dateValidation;
    }

    public int getIdDemandeProformaCoteFournisseur() {
        return idDemandeProformaCoteFournisseur;
    }

    public void setIdDemandeProformaCoteFournisseur(int idDemandeProformaCoteFournisseur) {
        this.idDemandeProformaCoteFournisseur = idDemandeProformaCoteFournisseur;
    }

    public ValidationDemandeProformaCoteFournisseur() {

    }

    public ValidationDemandeProformaCoteFournisseur(int idValidationDemandeProformaCoteFournisseur, Timestamp dateValidation, int idDemandeProformaCoteFournisseur) {
        this.setIdValidationDemandeProformaCoteFournisseur(idValidationDemandeProformaCoteFournisseur);
        this.setDateValidation(dateValidation);
        this.setIdDemandeProformaCoteFournisseur(idDemandeProformaCoteFournisseur);
    }

    public static void inserer(int idDemandeProformaCoteFournisseur)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO validationDemandeProformaCoteFournisseur (dateValidation, idDemandeProformaCoteFournisseur) VALUES (?, ?)";
        try {
            if(connection == null)connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(2, idDemandeProformaCoteFournisseur);
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

    public static void valider(DemandeProformaCoteFournisseur demandeProformaCoteFournisseur)throws Exception{
        demandeProformaCoteFournisseur.valider();
        ValidationDemandeProformaCoteFournisseur.inserer(demandeProformaCoteFournisseur.getIdDemandeProformaCoteFournisseur());
    }
}
