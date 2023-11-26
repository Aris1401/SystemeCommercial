package com.commercial.gestion.model.stock;

import com.commercial.gestion.model.Article;
import com.commercial.gestion.model.Unite;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;

public class LigneStock {
    Timestamp dateCourante = new Timestamp(System.currentTimeMillis());
    double quantite;
    Article article;
    Unite unite;
    double montant;
    double prixUnitaireMoyen;

    public Timestamp getDateCourante() {
        return dateCourante;
    }

    public void setDateCourante(Timestamp dateCourante) {
        this.dateCourante = dateCourante;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPrixUnitaireMoyen() {
        if (prixUnitaireMoyen == 0) {
            prixUnitaireMoyen = getQuantite() == 0 ? 0 : getMontant() / getQuantite();
        }
        return prixUnitaireMoyen;
    }

    public void setPrixUnitaireMoyen(double prixUnitaireMoyen) {
        this.prixUnitaireMoyen = prixUnitaireMoyen;
    }

    public static LigneStock getEtatStockArticle(int idArticle, int idUnite) throws Exception {
        // Obtenir article
        Article article = Article.getArticleById(idArticle);

        if (idUnite == -1) {
            // Obtenir le min de l'equivalence
            UniteEquivalence uniteEquivalence = UniteEquivalence.obtenirMinUniteEquivalencePour(idArticle);
            idUnite = uniteEquivalence.getIdUnite();
        }

        // Obtenir les mouvements de l'articles
        ArrayList<MouvementStock> mouvementsArticle = MouvementStock.obtenirMouvementsArticle(idArticle);
        System.out.println("----------" + idArticle + " : " + mouvementsArticle.isEmpty());
        if (mouvementsArticle.isEmpty()) return null;
        LigneStock ligneStock = new LigneStock();

        double montantTotal = 0;
        double quantiteTotal = 0;
        for (MouvementStock mouvementStock : mouvementsArticle) {
            quantiteTotal += UniteEquivalence.convertTo(mouvementStock.getIdArticle(), mouvementStock.getIdUnite(), idUnite, mouvementStock.getEntree());
            quantiteTotal -= UniteEquivalence.convertTo(mouvementStock.getIdArticle(), mouvementStock.getIdUnite(), idUnite, mouvementStock.getSortie());

            if (mouvementStock.getEntree() > 0) montantTotal += UniteEquivalence.convertTo(mouvementStock.getIdArticle(), mouvementStock.getIdUnite(), idUnite, mouvementStock.prixUnitaire);
        }

        Unite unite = Unite.getById(idUnite);

        ligneStock.setMontant(montantTotal);
        ligneStock.setQuantite(quantiteTotal);
        ligneStock.setArticle(article);
        ligneStock.setUnite(unite);
        ligneStock.setMontant(montantTotal);
        ligneStock.setQuantite(quantiteTotal);
        ligneStock.getPrixUnitaireMoyen();
        ligneStock.setDateCourante(new Timestamp(System.currentTimeMillis()));

        return ligneStock;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
