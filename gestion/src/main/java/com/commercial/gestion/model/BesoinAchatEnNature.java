package com.commercial.gestion.model;

import java.util.ArrayList;

public class BesoinAchatEnNature {
    Article article;
    Unite unite;
    double nombreTotal = 0;
    ArrayList<BesoinAchat> besoinAchats;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getNombreTotal() {
        return nombreTotal;
    }

    public void setNombreTotal(double nombreTotal) {
        this.nombreTotal = nombreTotal;
    }

    public ArrayList<BesoinAchat> getBesoinAchats() {
        return besoinAchats;
    }

    public void setBesoinAchats(ArrayList<BesoinAchat> besoinAchats) {
        this.besoinAchats = besoinAchats;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public void addToBesoinsAchats(BesoinAchat besoinAchat) {
        if (this.besoinAchats == null) this.besoinAchats = new ArrayList<>();

        this.besoinAchats.add(besoinAchat);
    }

    public static ArrayList<BesoinAchatEnNature> obtenirBesoinsEnNature() {
        // Obtenir tout les besoins
        ArrayList<BesoinAchat> besoinsAchats = BesoinAchat.allOpenBesoinAchat();

        ArrayList<BesoinAchatEnNature> besoinsEnNatures = new ArrayList<>();
        for (BesoinAchat besoinAchat : besoinsAchats) {
            ArrayList<ArticleBesoinAchat> articles = besoinAchat.getArticlesBesoinAchat();

            for (ArticleBesoinAchat article : articles) {
                int occurence = 0;

                for (int i = 0; i < besoinsEnNatures.size(); i++) {
                    if (besoinsEnNatures.get(i).getArticle().getIdArticle() == article.getIdArticle()) {
                        if (besoinsEnNatures.get(i).getUnite().getIdUnite() == article.getIdUnite()) {
                            occurence++;

                            besoinsEnNatures.get(i).setNombreTotal(besoinsEnNatures.get(i).getNombreTotal() + article.getQuantite());
                        }
                    }
                }

                if (occurence == 0) {
                    BesoinAchatEnNature besoinAchatEnNature = new BesoinAchatEnNature();
                    besoinAchatEnNature.setNombreTotal(article.getQuantite());
                    besoinAchatEnNature.setArticle(Article.getArticleById(article.getIdArticle()));
                    besoinAchatEnNature.setUnite(Unite.getById(article.getIdUnite()));
                    besoinAchatEnNature.addToBesoinsAchats(besoinAchat);

                    besoinsEnNatures.add(besoinAchatEnNature);
                }
            }
        }

        return besoinsEnNatures;
    }
}
