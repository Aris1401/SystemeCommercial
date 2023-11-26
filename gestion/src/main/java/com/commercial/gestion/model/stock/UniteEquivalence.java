package com.commercial.gestion.model.stock;

import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.model.Article;
import com.commercial.gestion.model.Unite;

import java.sql.Connection;
import java.util.ArrayList;

public class UniteEquivalence extends GenericDAO<UniteEquivalence> {
    @PrimaryKey
    int idUniteEquivalence;
    int idUnite;
    int idArticle;
    double quantite;

    public int getIdUniteEquivalence() {
        return idUniteEquivalence;
    }

    public void setIdUniteEquivalence(int idUniteEquivalence) {
        this.idUniteEquivalence = idUniteEquivalence;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public static ArrayList<UniteEquivalence> obtenirUniteEquivalencePourArticle(int idArticle) {
        ArrayList<UniteEquivalence> uniteEquivalences = new ArrayList<>();

        UniteEquivalence uniteEquivalence = new UniteEquivalence();
        try {
            uniteEquivalence.addToSelection("idArticle", idArticle, "");

            Connection c = ConnectTo.postgreS();

            uniteEquivalences = uniteEquivalence.getFromDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (uniteEquivalences.isEmpty()) {
            UniteEquivalence uniteEquivalence1 = new UniteEquivalence();
            uniteEquivalence1.setIdArticle(idArticle);
            uniteEquivalence1.setIdUnite(1);
            uniteEquivalence1.setQuantite(1);

            uniteEquivalences.add(uniteEquivalence1);
        }

        return uniteEquivalences;
    }

    public static UniteEquivalence obtenirUniteEquivalencePourArticleEtUnite(int idArticle, int idUnite) {
        ArrayList<UniteEquivalence> uniteEquivalences = new ArrayList<>();

        UniteEquivalence uniteEquivalence = new UniteEquivalence();
        try {
            uniteEquivalence.addToSelection("idArticle", idArticle, "and");
            uniteEquivalence.addToSelection("idUnite", idUnite, "");

            Connection c = ConnectTo.postgreS();

            uniteEquivalences = uniteEquivalence.getFromDatabase(c);
            uniteEquivalence = uniteEquivalences.isEmpty() ? null : uniteEquivalences.get(0);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return uniteEquivalence;
    }

    public static boolean isUniteForArticle(int idArticle, int idUnite) {
        ArrayList<UniteEquivalence> uniteEquivalences = obtenirUniteEquivalencePourArticle(idArticle);

        int occurence = 0;
        for (UniteEquivalence uniteEquivalence : uniteEquivalences) {
            if (uniteEquivalence.getIdUnite() == idUnite) {
                occurence++;
            }
        }

        return occurence > 0;
    }

    public static UniteEquivalence obtenirMinUniteEquivalencePour(int idArticle) {
        ArrayList<UniteEquivalence> uniteEquivalences = obtenirUniteEquivalencePourArticle(idArticle);

        UniteEquivalence minUnite = null;
        if (uniteEquivalences.isEmpty()) {
            minUnite = new UniteEquivalence();
            minUnite.setQuantite(1);
            minUnite.setIdArticle(idArticle);
            minUnite.setIdUnite(1);
        } else {
            minUnite = uniteEquivalences.get(0);
            for (int i = 0; i < uniteEquivalences.size(); i++) {
                if (minUnite.getQuantite() > uniteEquivalences.get(0).getQuantite()) {
                    minUnite = uniteEquivalences.get(0);
                }
            }
        }
        return minUnite;
    }

    public static double convertTo(int idArticle, int idUniteFrom, int idUniteTo, double value) throws Exception {
        if (!isUniteForArticle(idArticle, idUniteFrom) || !isUniteForArticle(idArticle, idUniteTo)) throw new Exception("Unite invalide pour article.");

        UniteEquivalence uniteEquivalenceFrom = UniteEquivalence.obtenirUniteEquivalencePourArticleEtUnite(idArticle, idUniteFrom);
        if (uniteEquivalenceFrom == null) {
            uniteEquivalenceFrom = new UniteEquivalence();
            uniteEquivalenceFrom.setQuantite(1);
        }

        UniteEquivalence uniteEquivalenceTo = UniteEquivalence.obtenirUniteEquivalencePourArticleEtUnite(idArticle, idUniteTo);
        if (uniteEquivalenceTo == null) {
            uniteEquivalenceTo = new UniteEquivalence();
            uniteEquivalenceTo.setQuantite(1);
        }

        return (value * uniteEquivalenceFrom.getQuantite()) / (uniteEquivalenceTo.getQuantite());
    }

    public Unite getUnite() {
        return Unite.getById(idUnite);
    }

    public Article getArticle() {
        return Article.getArticleById(idArticle);
    }
}
