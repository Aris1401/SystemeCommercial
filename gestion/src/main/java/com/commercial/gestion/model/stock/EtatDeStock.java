package com.commercial.gestion.model.stock;

import com.commercial.gestion.model.Article;
import com.commercial.gestion.page.protection.IProtection;

import java.util.ArrayList;

public class EtatDeStock implements IProtection {
    ArrayList<LigneStock> ligneStocks = new ArrayList<>();

    public ArrayList<LigneStock> getLigneStocks() {
        return ligneStocks;
    }

    public void setLigneStocks(ArrayList<LigneStock> ligneStocks) {
        this.ligneStocks = ligneStocks;
    }

    public static EtatDeStock obtenirEtatStockArticle(int idArticle, int idUnite) {
        EtatDeStock etatDeStock = new EtatDeStock();

        try {
            LigneStock ligneStock = LigneStock.getEtatStockArticle(idArticle, idUnite);
            etatDeStock.ligneStocks.add(ligneStock);
        } catch (Exception e) {
            return null;
        }

        return etatDeStock;
    }
    public static EtatDeStock obtenirEtatStock(int idUnite) {
        EtatDeStock etatDeStock = new EtatDeStock();
        if (idUnite == -1) idUnite = 1;

        ArrayList<Article> articles = Article.allArticle();

        ArrayList<LigneStock> ligneStocks = new ArrayList<>();
        for (Article article : articles) {
            try {
                LigneStock ligneStock = LigneStock.getEtatStockArticle(article.getIdArticle(), idUnite);
                if (ligneStock != null) ligneStocks.add(ligneStock);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }

        etatDeStock.setLigneStocks(ligneStocks);
        return etatDeStock;
    }

    @Override
    public ArrayList<Integer> getAuthorizedProfiles() {
        ArrayList<Integer> authorized = new ArrayList<>();
        authorized.add(4);
        return authorized;
    }
}
