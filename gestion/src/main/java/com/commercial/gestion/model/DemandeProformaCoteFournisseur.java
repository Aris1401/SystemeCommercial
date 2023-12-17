package com.commercial.gestion.model;

import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.*;
import java.util.ArrayList;
import com.commercial.gestion.configuration.DemandeConfiguration;

public class DemandeProformaCoteFournisseur {
    public static final String CSV_SAVE_PATH = "./demandes";
    private int idDemandeProformaCoteFournisseur;
    private Timestamp dateDemande;
    private int idArticle;
    private int idUnite;
    private int quantite;
    private int etat;
    private int idFournisseur;
    private String lienEmail;
    public int getIdDemandeProformaCoteFournisseur() {
        return idDemandeProformaCoteFournisseur;
    }

    public void setIdDemandeProformaCoteFournisseur(int idDemandeProformaCoteFournisseur) {
        this.idDemandeProformaCoteFournisseur = idDemandeProformaCoteFournisseur;
    }

    public Timestamp getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Timestamp dateDemande) {
        this.dateDemande = dateDemande;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getLienEmail() {
        return lienEmail;
    }

    public void setLienEmail(String lienEmail) {
        this.lienEmail = lienEmail;
    }

    public DemandeProformaCoteFournisseur() {

    }

    public DemandeProformaCoteFournisseur(int idDemandeProformaCoteFournisseur, Timestamp dateDemande, int idArticle, int idUnite, int quantite, int etat, int idFournisseur, String lienEmail) {
        this.setIdDemandeProformaCoteFournisseur(idDemandeProformaCoteFournisseur);
        this.setDateDemande(dateDemande);
        this.setIdArticle(idArticle);
        this.setIdUnite(idUnite);
        this.setQuantite(quantite);
        this.setEtat(etat);
        this.setIdFournisseur(idFournisseur);
        this.setLienEmail(lienEmail);
    }

    public static DemandeProformaCoteFournisseur obtenirById(int id)throws Exception{
        Connection connection = null;
        DemandeProformaCoteFournisseur demande = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM DemandeProformaCoteFournisseur WHERE idDemandeProformaCoteFournisseur = ?";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idDemande = resultSet.getInt("idDemandeProformaCoteFournisseur");
                Timestamp dateDemande = resultSet.getTimestamp("dateDemande");
                int idArticle = resultSet.getInt("idArticle");
                int idUnite = resultSet.getInt("idUnite");
                int quantite = resultSet.getInt("quantite");
                int etat = resultSet.getInt("etat");
                int idFournisseur = resultSet.getInt("idFournisseur");
                String lienEmail = resultSet.getString("lienEmail");

                demande = new DemandeProformaCoteFournisseur(idDemande, dateDemande, idArticle, idUnite, quantite, etat, idFournisseur, lienEmail);
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
        String query = "INSERT INTO DemandeProformaCoteFournisseur (dateDemande, idArticle, idUnite, quantite, idFournisseur, etat, lienEmail) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            if(connection == null)connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, this.getDateDemande());
            preparedStatement.setInt(2, this.getIdArticle());
            preparedStatement.setInt(3, this.getIdUnite());
            preparedStatement.setDouble(4, this.getQuantite());
            preparedStatement.setInt(5, this.getIdFournisseur());
            preparedStatement.setInt(6, this.getEtat());
            preparedStatement.setString(7, this.getLienEmail());

            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if (res.next()) {
                idDemandeProformaCoteFournisseur = res.getInt(1);
            }
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
        String query = "UPDATE DemandeProformaCoteFournisseur SET etat = "+DemandeConfiguration.VALIDER+" WHERE idDemandeProformaCoteFournisseur = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.getIdDemandeProformaCoteFournisseur());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("L'état a été mis à jour avec succès pour l'ID : " + this.getIdDemandeProformaCoteFournisseur());
            } else {
                System.out.println("Aucune entrée trouvée avec l'ID : " + this.getIdDemandeProformaCoteFournisseur());
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

    public static ArrayList<DemandeProformaCoteFournisseur> obtenirAllDemandesOuvertes()throws Exception{
        Connection connection = null;
        ArrayList<DemandeProformaCoteFournisseur> demandes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM DemandeProformaCoteFournisseur WHERE etat = 0";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idDemande = resultSet.getInt("idDemandeProformaCoteFournisseur");
                Timestamp dateDemande = resultSet.getTimestamp("dateDemande");
                int idArticle = resultSet.getInt("idArticle");
                int idUnite = resultSet.getInt("idUnite");
                int quantite = resultSet.getInt("quantite");
                int etat = resultSet.getInt("etat");
                int idFournisseur = resultSet.getInt("idFournisseur");
                String lienEmail = resultSet.getString("lienEmail");

                DemandeProformaCoteFournisseur demande = new DemandeProformaCoteFournisseur(idDemande, dateDemande, idArticle, idUnite, quantite, etat, idFournisseur, lienEmail);
                demandes.add(demande);
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

        return demandes;
    }

    public static ArrayList<DemandeProformaCoteFournisseur> obtenirAllDemandesFermees()throws Exception{
        Connection connection = null;
        ArrayList<DemandeProformaCoteFournisseur> demandes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM DemandeProformaCoteFournisseur WHERE etat = 30";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idDemande = resultSet.getInt("idDemandeProformaCoteFournisseur");
                Timestamp dateDemande = resultSet.getTimestamp("dateDemande");
                int idArticle = resultSet.getInt("idArticle");
                int idUnite = resultSet.getInt("idUnite");
                int quantite = resultSet.getInt("quantite");
                int etat = resultSet.getInt("etat");
                int idFournisseur = resultSet.getInt("idFournisseur");
                String lienEmail = resultSet.getString("lienEmail");

                DemandeProformaCoteFournisseur demande = new DemandeProformaCoteFournisseur(idDemande, dateDemande, idArticle, idUnite, quantite, etat, idFournisseur, lienEmail);
                demandes.add(demande);
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

        return demandes;
    }

    public boolean isValider(){
        return this.getEtat() == DemandeConfiguration.VALIDER;
    }

    public Article obtenirArticle() {
        return Article.getArticleById(idArticle);
    }

    public Unite obtenirUnite() {
        return Unite.getById(idUnite);
    }

    public Fournisseur obtenirFournisseur() {
        return Fournisseur.getFournisseurById(idFournisseur);
    }
}
