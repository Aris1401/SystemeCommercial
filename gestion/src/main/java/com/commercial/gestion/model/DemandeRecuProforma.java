package com.commercial.gestion.model;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.*;
import java.util.ArrayList;

public class DemandeRecuProforma {
    private int idDemandeRecuProforma;
    private int idFournisseurFrom;
    private String lienEmail;
    private int quantite;
    private int idArticle;
    private int idUnite;
    private Timestamp dateDemande;
    private int etat;

    public int getIdDemandeRecuProforma() {
        return idDemandeRecuProforma;
    }

    public void setIdDemandeRecuProforma(int idDemandeRecuProforma) {
        this.idDemandeRecuProforma = idDemandeRecuProforma;
    }

    public int getIdFournisseurFrom() {
        return idFournisseurFrom;
    }

    public void setIdFournisseurFrom(int idFournisseurFrom) {
        this.idFournisseurFrom = idFournisseurFrom;
    }

    public String getLienEmail() {
        return lienEmail;
    }

    public void setLienEmail(String lienEmail) {
        this.lienEmail = lienEmail;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public Timestamp getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Timestamp dateDemande) {
        this.dateDemande = dateDemande;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public DemandeRecuProforma() {

    }

    public DemandeRecuProforma(int idDemandeRecuProforma, int idFournisseurFrom, String lienEmail, int quantite, int idArticle, int idUnite, Timestamp dateDemande, int etat) {
        this.setIdDemandeRecuProforma(idDemandeRecuProforma);
        this.setIdFournisseurFrom(idFournisseurFrom);
        this.setLienEmail(lienEmail);
        this.setQuantite(quantite);
        this.setIdArticle(idArticle);
        this.setIdUnite(idUnite);
        this.setDateDemande(dateDemande);
        this.setEtat(etat);
    }

    public static DemandeRecuProforma getById(int id)throws Exception{
        Connection connection = null;
        DemandeRecuProforma demande = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM DemandeRecuProforma WHERE idDemandeRecuProforma = ?";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idDemande = resultSet.getInt("idDemandeRecuProforma");
                Timestamp dateDemande = resultSet.getTimestamp("dateDemande");
                int idArticle = resultSet.getInt("idArticle");
                int idUnite = resultSet.getInt("idUnite");
                int quantite = resultSet.getInt("quantite");
                int etat = resultSet.getInt("etat");
                int idFournisseur = resultSet.getInt("idFournisseurFrom");
                String lienEmail = resultSet.getString("lienEmail");

                demande = new DemandeRecuProforma(idDemande, idFournisseur, lienEmail, quantite, idArticle, idUnite, dateDemande, etat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon le besoin
        } finally {
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

        return demande;
    }

    public void inserer()throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO DemandeRecuProforma(idFournisseurFrom, lienEmail, quantite, idArticle, idunite, dateDemande, etat) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            if(connection == null)connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.getIdFournisseurFrom());
            preparedStatement.setString(2, this.getLienEmail());
            preparedStatement.setInt(3, this.getQuantite());
            preparedStatement.setInt(4, this.getIdArticle());
            preparedStatement.setInt(5, this.getIdUnite());
            preparedStatement.setTimestamp(6, this.getDateDemande());
            preparedStatement.setInt(7, this.getEtat());

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

    public void valider()throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE DemandeRecuProforma SET etat = "+ DemandeConfiguration.VALIDER+" WHERE idDemandeRecuProforma = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.getIdDemandeRecuProforma());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("L'état a été mis à jour avec succès pour l'ID : " + this.getIdDemandeRecuProforma());
            } else {
                System.out.println("Aucune entrée trouvée avec l'ID : " + this.getIdDemandeRecuProforma());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon le besoin
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
    public static ArrayList<DemandeRecuProforma> getAllDemandesRecuOuvertes() throws Exception {
        Connection connection = null;
        ArrayList<DemandeRecuProforma> demandes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM DemandeRecuProforma WHERE etat = 0";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idDemande = resultSet.getInt("idDemandeRecuProforma");
                Timestamp dateDemande = resultSet.getTimestamp("dateDemande");
                int idArticle = resultSet.getInt("idArticle");
                int idUnite = resultSet.getInt("idUnite");
                int quantite = resultSet.getInt("quantite");
                int etat = resultSet.getInt("etat");
                int idFournisseur = resultSet.getInt("idFournisseurFrom");
                String lienEmail = resultSet.getString("lienEmail");

                DemandeRecuProforma demande = new DemandeRecuProforma(idDemande, idFournisseur, lienEmail, quantite, idArticle, idUnite, dateDemande, etat);
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return demandes;
    }

    public static ArrayList<DemandeRecuProforma> getAllDemandesRecuFermees() throws Exception {
        Connection connection = null;
        ArrayList<DemandeRecuProforma> demandes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM DemandeRecuProforma WHERE etat = 30";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idDemande = resultSet.getInt("idDemandeRecuProforma");
                Timestamp dateDemande = resultSet.getTimestamp("dateDemande");
                int idArticle = resultSet.getInt("idArticle");
                int idUnite = resultSet.getInt("idUnite");
                int quantite = resultSet.getInt("quantite");
                int etat = resultSet.getInt("etat");
                int idFournisseur = resultSet.getInt("idFournisseurFrom");
                String lienEmail = resultSet.getString("lienEmail");

                DemandeRecuProforma demande = new DemandeRecuProforma(idDemande, idFournisseur, lienEmail, quantite, idArticle, idUnite, dateDemande, etat);
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return demandes;
    }
}
