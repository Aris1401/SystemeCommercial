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
public class BesoinAchat extends BDD
{
    int idBesoinAchat;
    int idService;
    Timestamp dateBesoin;
    Timestamp dateCloture;
    int statusBesoin;

    public int getIdBesoinAchat() {
        return idBesoinAchat;
    }

    public void setIdBesoinAchat(int idBesoinAchat) {
        this.idBesoinAchat = idBesoinAchat;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public Timestamp getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Timestamp dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public Timestamp getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Timestamp dateCloture) {
        this.dateCloture = dateCloture;
    }

    public int getStatusBesoin() {
        return statusBesoin;
    }

    public void setStatusBesoin(int statusBesoin) {
        this.statusBesoin = statusBesoin;
    }

////////////////////////////////////////////////////////////////////////////
    public boolean insertBesoinAchat(String idService,String dateBesoin,String dateCloture,String statusBesoin)
    {
        boolean insert=false;
        BesoinAchat besoin=new BesoinAchat();
        besoin.setIdService(Integer.parseInt(idService));
        besoin.setDateBesoin(Timestamp.valueOf(dateBesoin));
        besoin.setDateCloture(Timestamp.valueOf(dateCloture));
        besoin.setStatusBesoin(Integer.parseInt(statusBesoin));
        besoin.dontSave("idBesoinAchat");
        besoin.save();
        insert=true;
        return insert;

    }
////////////////////////////////////////////////////////////////////////////
public ArrayList<BesoinAchat> allOpenBesoinAchat()
{
    String condition="status = 1";
    BesoinAchat besoinAchat=new BesoinAchat();
   ArrayList<String[]> allBesoinAchatBDD=besoinAchat.select(condition);
   ArrayList<BesoinAchat> allBesoinAchat=new ArrayList<BesoinAchat>();
   for(int i=0;i<allBesoinAchatBDD.size();i++)
   {
       BesoinAchat b=new BesoinAchat();
       b.setIdBesoinAchat(Integer.parseInt(allBesoinAchatBDD.get(i)[0]));
       b.setIdService(Integer.parseInt(allBesoinAchatBDD.get(i)[1]));
       b.setDateBesoin(Timestamp.valueOf(allBesoinAchatBDD.get(i)[2]));
       b.setDateCloture(Timestamp.valueOf(allBesoinAchatBDD.get(i)[3]));
       b.setStatusBesoin(Integer.parseInt(allBesoinAchatBDD.get(i)[4]));
       allBesoinAchat.add(b);
   }
    return allBesoinAchat;
}
////////////////////////////////////////////////////////////////////////////
public ArrayList<BesoinAchat> allClosedBesoinAchat()
{
    String condition="status = 0";
    BesoinAchat besoinAchat=new BesoinAchat();
    ArrayList<String[]> allBesoinAchatBDD=besoinAchat.select(condition);
    ArrayList<BesoinAchat> allBesoinAchat=new ArrayList<BesoinAchat>();
    for(int i=0;i<allBesoinAchatBDD.size();i++)
    {
        BesoinAchat b=new BesoinAchat();
        b.setIdBesoinAchat(Integer.parseInt(allBesoinAchatBDD.get(i)[0]));
        b.setIdService(Integer.parseInt(allBesoinAchatBDD.get(i)[1]));
        b.setDateBesoin(Timestamp.valueOf(allBesoinAchatBDD.get(i)[2]));
        b.setDateCloture(Timestamp.valueOf(allBesoinAchatBDD.get(i)[3]));
        b.setStatusBesoin(Integer.parseInt(allBesoinAchatBDD.get(i)[4]));
        allBesoinAchat.add(b);
    }
    return allBesoinAchat;
}
///////////////////////////////////////////////////////////////////////////
public static BesoinAchat getBesoinAchatById(String idBesoinAchat)
{
    BesoinAchat b=new BesoinAchat();
    String condition="idBesoinAchat ="+ idBesoinAchat;
    ArrayList<String[]> allBesoinAchatBDD=b.select(condition);
    for(int i=0;i<allBesoinAchatBDD.size();i++)
    {

        b.setIdBesoinAchat(Integer.parseInt(allBesoinAchatBDD.get(i)[0]));
        b.setIdService(Integer.parseInt(allBesoinAchatBDD.get(i)[1]));
        b.setDateBesoin(Timestamp.valueOf(allBesoinAchatBDD.get(i)[2]));
        b.setDateCloture(Timestamp.valueOf(allBesoinAchatBDD.get(i)[3]));
        b.setStatusBesoin(Integer.parseInt(allBesoinAchatBDD.get(i)[4]));
    }

    return b;
}
///////////////////////////////////////////////////////////////////////////

}
