/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class ProformaBesoinAchat extends BDD
{
    int idProformaBesoinAchat;

    int idProforma;
    int idBesoinAchat;
    Timestamp dateLivraison;

    public int getIdProformaBesoinAchat() {
        return idProformaBesoinAchat;
    }

    public void setIdProformaBesoinAchat(int idProformaBesoinAchat) {
        this.idProformaBesoinAchat = idProformaBesoinAchat;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public Timestamp getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Timestamp dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    //////////////////////////////////////////////////////
public ArrayList<ProformaBesoinAchat> getProformaForBesoinAchat(int idBesoinAchat)
{

    ProformaBesoinAchat proforma=new ProformaBesoinAchat();
    String condition="idBesoin";
    ArrayList<String[]> allProformaBDD=proforma.select();
    ArrayList<ProformaBesoinAchat> allProforma=new ArrayList<ProformaBesoinAchat>();
    for(int i=0;i< allProformaBDD.size();i++)
    {
        ProformaBesoinAchat p=new ProformaBesoinAchat();
        p.setIdProformaBesoinAchat(Integer.parseInt(allProformaBDD.get(i)[0]));
        p.setIdProforma(Integer.parseInt(allProformaBDD.get(i)[1]));
        p.setIdBesoinAchat(Integer.parseInt(allProformaBDD.get(i)[2]));
        p.setDateLivraison(Timestamp.valueOf(allProformaBDD.get(i)[3]));

        allProforma.add(p);
    }
    return allProforma;
}
//////////////////////////////////////////////////////
}
