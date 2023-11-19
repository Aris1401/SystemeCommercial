/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class Article extends BDD
{
    int idArticle;
    String nom;
    String descriptionArticle;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

/////////////////////////////////////////////////////////////
public static ArrayList<Article> allArticle()
{
    Article article=new Article();
    ArrayList<String[]> allArticleBDD=article.select();
    ArrayList<Article> allArticle=new ArrayList<Article>();
    for(int i=0;i<allArticleBDD.size();i++)
    {
        Article a=new Article();
        a.setIdArticle(Integer.parseInt(allArticleBDD.get(i)[0]));
        a.setNom(allArticleBDD.get(i)[1]);
        a.setDescriptionArticle(allArticleBDD.get(i)[2]);
        allArticle.add(a);

    }
    return allArticle;
}
/////////////////////////////////////////////////////////////
public static Article getArticleById(int idArticle)
{
    Article a=new Article();
    String condition="where idArticle= "+idArticle;
    ArrayList<String[]> allArticleBDD=a.select(condition);
    for (String[] strings : allArticleBDD) {
        a.setIdArticle(Integer.parseInt(strings[0]));
        a.setNom(strings[1]);
        a.setDescriptionArticle(strings[2]);
    }
    return a;
}
/////////////////////////////////////////////////////////////
public boolean insertArticle(String nom,String descriptionArticle)
{
    boolean insert =false;
    Article article=new Article();
    article.setNom(nom);
    article.setDescriptionArticle(descriptionArticle);
    article.dontSave("idArticle");
    article.save();
    insert=true;
    return insert;
}
/////////////////////////////////////////////////////////////
}
