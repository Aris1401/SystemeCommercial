package com.commercial.gestion.model.stock;

import com.commercial.gestion.aris.bdd.annotations.ExcludeFromInsertion;
import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.model.Article;
import com.commercial.gestion.model.Unite;
import com.commercial.gestion.model.stock.dto.EntreeDTO;
import com.commercial.gestion.model.stock.dto.SortieDTO;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class MouvementStock extends GenericDAO<MouvementStock>{
    @PrimaryKey
    @ExcludeFromInsertion
    int idMouvementStock;
    double entree;
    double sortie;
    Timestamp dateMouvement;
    int idArticle;
    int idUnite;
    double prixUnitaire;

    public int getIdMouvementStock() {
        return idMouvementStock;
    }

    public void setIdMouvementStock(int idMouvementStock) {
        this.idMouvementStock = idMouvementStock;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) throws Exception {
        if (sortie > 0) {
            double quantiteRestante = MouvementStock.getResteStock(getIdArticle(), getIdUnite());
            if (quantiteRestante - sortie <= 0) throw new Exception("Stock insuffisant.");
        }

        this.sortie = sortie;
    }

    public Timestamp getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Timestamp dateMouvement) throws Exception {
        MouvementStock recentMouvement = MouvementStock.closestMouvementStock(getIdArticle());
        if (recentMouvement != null) if (dateMouvement.before(recentMouvement.dateMouvement)) {
            String newLine = System.lineSeparator();
            throw new Exception("Date ulterieure impossible. Recente: " + newLine + recentMouvement.dateMouvement.toString());
        }

        this.dateMouvement = dateMouvement;
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

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public ArrayList<MouvementStock> allMouvements() {
        ArrayList<MouvementStock> mouvementStocks = new ArrayList<>();
        try {
            Connection c = ConnectTo.postgreS();
            mouvementStocks = this.getFromDatabase(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mouvementStocks;
    }

    public Unite getUnite() {
        return Unite.getById(idUnite);
    }

    public Article getArticle() {
        return Article.getArticleById(idArticle);
    }

    public static ArrayList<MouvementStock> obtenirMouvementsArticle(int idArticle) {
        ArrayList<MouvementStock> mouvements = new ArrayList<>();

        try {
            MouvementStock mouvementStock = new MouvementStock();
            mouvementStock.addToSelection("idArticle", idArticle, "");

            Connection c = ConnectTo.postgreS();

            mouvements = mouvementStock.getFromDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mouvements;
    }

    public static double getResteStock(int idArticle, int idUnite) throws Exception {
        LigneStock ligneStock = LigneStock.getEtatStockArticle(idArticle, idUnite);
        return ligneStock == null ? 0 : ligneStock.getQuantite();
    }

    public static boolean estEpuisee(int idArticle, int idUnite) throws Exception {
        return getResteStock(idArticle, idUnite) < 0;
    }

    public static MouvementStock faireUneSortie(int idArticle, int idUnite, double quantite, Timestamp dateSortie) throws Exception {
        if (!UniteEquivalence.isUniteForArticle(idArticle, idUnite)) throw new Exception("Unite invalide pour l'article.");
        if (estEpuisee(idArticle, idUnite)) throw new Exception("Stock epuisee.");

        MouvementStock mouvementStock = new MouvementStock();
        mouvementStock.setEntree(0);
        mouvementStock.setIdArticle(idArticle);
        mouvementStock.setIdUnite(idUnite);
        mouvementStock.setPrixUnitaire(0);
        mouvementStock.setSortie(quantite);
        mouvementStock.setDateMouvement(dateSortie);

        Connection c = ConnectTo.postgreS();
        int id = mouvementStock.saveInDatabse(c);
        c.close();

        mouvementStock.setIdMouvementStock(id);

        return mouvementStock;
    }

    public static MouvementStock faireUneEntree(int idArticle, int idUnite, double quantite, double prixUnitaire, Timestamp dateEntree) throws Exception {
        if (!UniteEquivalence.isUniteForArticle(idArticle, idUnite)) throw new Exception("Unite invalide pour l'article.");

        MouvementStock mouvementStock = new MouvementStock();
        mouvementStock.setEntree(quantite);
        mouvementStock.setIdArticle(idArticle);
        mouvementStock.setIdUnite(idUnite);
        mouvementStock.setPrixUnitaire(prixUnitaire);
        mouvementStock.setSortie(0);
        mouvementStock.setDateMouvement(dateEntree);

        Connection c = ConnectTo.postgreS();
        int id = mouvementStock.saveInDatabse(c);
        c.close();

        mouvementStock.setIdMouvementStock(id);

        return mouvementStock;
    }

    public static MouvementStock closestMouvementStock(int idArticle) {
        ArrayList<MouvementStock> mouvements = obtenirMouvementsArticle(idArticle);

        if (!mouvements.isEmpty()) {
            mouvements.sort(Comparator.comparing(MouvementStock::getDateMouvement).reversed());

            // Return the most recent movement
            return mouvements.get(0);
        } else {
            return null; // No movements found for the given article
        }
    }

    public static MouvementStock faireUneEntree(EntreeDTO entreeDTO) throws Exception {
        return faireUneEntree(entreeDTO.getIdArticle(), entreeDTO.getIdUnite(), entreeDTO.getEntree(), entreeDTO.getPrixUnitaire(), entreeDTO.getDateMouvement());
    }

    public static MouvementStock faireUneSortie(SortieDTO sortieDTO) throws Exception {
        return faireUneSortie(sortieDTO.getIdArticle(), sortieDTO.getIdUnite(), sortieDTO.getSortie(), sortieDTO.getDateMouvement());
    }
}
