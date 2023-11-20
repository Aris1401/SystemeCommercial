/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commercial.gestion.model;

import com.commercial.gestion.BDDIante.BDD;

import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class ProfilUtilisateur extends BDD
{
    int idProfilUtilisateur;
    int idUtilisateur;
    int idProfil;
    Timestamp dateAjout;  

    public int getIdProfilUtilisateur() {
        return idProfilUtilisateur;
    }

    public void setIdProfilUtilisateur(int idProfilUtilisateur) {
        this.idProfilUtilisateur = idProfilUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }
    /////////////////////////////////////////////////////////////////////////////
    public ArrayList<ProfilUtilisateur> allProfilUser()
    {
        ProfilUtilisateur pp=new ProfilUtilisateur();
        ArrayList<String[]>allBDD=pp.select();
        ArrayList<ProfilUtilisateur> all=new ArrayList<ProfilUtilisateur>();
        for(int i=0;i< allBDD.size();i++)
        {
            ProfilUtilisateur p=new ProfilUtilisateur();
            p.setIdProfilUtilisateur(Integer.parseInt(allBDD.get(i)[0]));
            p.setIdUtilisateur(Integer.parseInt(allBDD.get(i)[1]));
            p.setIdProfil(Integer.parseInt(allBDD.get(i)[2]));
            p.setDateAjout(Timestamp.valueOf(allBDD.get(i)[3]));

            all.add(p);
        }
        return all;
    }
    ////////////////////////////////////////////////////////////////////////////
    public ProfilUtilisateur getProfil(String email,String motDePasse,Utilisateur user)
    {
       ProfilUtilisateur p=new ProfilUtilisateur();
       user=new Utilisateur();
       user=user.connect(email,motDePasse);
       ArrayList<ProfilUtilisateur>all= allProfilUser();
       for(int i=0;i<all.size();i++)
        {
            if(user.getIdUtilisateur()==all.get(i).getIdUtilisateur())
            {
                p.setIdProfilUtilisateur(all.get(i).getIdProfilUtilisateur());
                p.setIdUtilisateur(all.get(i).getIdUtilisateur());
                p.setIdProfil(all.get(i).getIdProfil());
                p.setDateAjout(all.get(i).getDateAjout());
            }
        }
       return p;
    }
    ////////////////////////////////////////////////////////////////////////////
    
}
