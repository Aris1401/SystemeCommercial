/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class DemandeAjoutArticle extends BDD
{
    int idDemandeAjoutArticle;
    String nomArticle;
    Timestamp dateLivraison;

    public int getIdDemandeAjoutArticle() {
        return idDemandeAjoutArticle;
    }

    public void setIdDemandeAjoutArticle(int idDemandeAjoutArticle) {
        this.idDemandeAjoutArticle = idDemandeAjoutArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
///////////////////////////////////////////////////////////
    public ArrayList<DemandeAjoutArticle> allDemandeAjoutArticle()
    {
        DemandeAjoutArticle demandeAjoutArticle=new DemandeAjoutArticle();
        ArrayList<String[]> allDemandeAjoutArticleBDD=demandeAjoutArticle.select();
        ArrayList<DemandeAjoutArticle> allDemandeAjoutArticle=new ArrayList<DemandeAjoutArticle>();
        for(int i=0;i< allDemandeAjoutArticleBDD.size();i++)
        {
            DemandeAjoutArticle d=new DemandeAjoutArticle();
            d.setIdDemandeAjoutArticle(Integer.parseInt(allDemandeAjoutArticleBDD.get(i)[0]));
            d.setNomArticle(allDemandeAjoutArticleBDD.get(i)[1]);
            d.setDateLivraison(Timestamp.valueOf(allDemandeAjoutArticleBDD.get(i)[2]));

            allDemandeAjoutArticle.add(d);
        }
        return allDemandeAjoutArticle;

    }
///////////////////////////////////////////////////////////
    public boolean insertDemandeAjoutArticle(String nomArticle,String dateLivraison)
    {
        boolean insert=false;
        DemandeAjoutArticle d=new DemandeAjoutArticle();
        d.setNomArticle(nomArticle);
        d.setDateLivraison(Timestamp.valueOf(dateLivraison));
        d.dontSave("idDemandeAjoutArticle");
        d.save();
        insert=true;
        return insert;
    }
///////////////////////////////////////////////////////////
    
}
