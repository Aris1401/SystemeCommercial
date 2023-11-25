package com.commercial.gestion.controller;

import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.model.Fournisseur;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;

@RestController
public class FournisseurController {
    @GetMapping("/fournisseurs")
    public ArrayList<Fournisseur> allFournisseur() {
        return Fournisseur.allFournisseur();
    }

    @PostMapping("/fournisseurs")
    public Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur) {
        fournisseur.dontSave("idFournisseur");
        int id = fournisseur.saveId();
        fournisseur.setIdFournisseur(id);

        return fournisseur;
    }

    @GetMapping("/fournisseurs/{id}")
    public Fournisseur getFournisseur(@PathVariable("id") int id) {
        Fournisseur fournisseur = Fournisseur.getFournisseurById(id);
        return fournisseur;
    }

    @PostMapping("/fournisseurs/update")
    public void updateFournisseur(@RequestBody Fournisseur fournisseur) {
        GenericDAO<Fournisseur> fournisseurGenericDAO = new GenericDAO<>(Fournisseur.class);
        try {
            Connection c = ConnectTo.postgreS();

            fournisseurGenericDAO.addToSelection("idFournisseur", fournisseur.getIdFournisseur(), "");
            fournisseurGenericDAO.addToSetUpdate("nom", fournisseur.getNom());
            fournisseurGenericDAO.addToSetUpdate("contact", fournisseur.getContact());
            fournisseurGenericDAO.addToSetUpdate("nomResponsable", fournisseur.getNomResponsable());
            fournisseurGenericDAO.addToSetUpdate("contactResponsable", fournisseur.getContactResponsable());
            fournisseurGenericDAO.addToSetUpdate("email", fournisseur.getEmail());
            fournisseurGenericDAO.addToSetUpdate("addresse", fournisseur.getAddresse());
            fournisseurGenericDAO.addToSetUpdate("localisation", fournisseur.getLocalisation());
            fournisseurGenericDAO.addToSetUpdate("descriptionFournisseur", fournisseur.getDescriptionFournisseur());
            fournisseurGenericDAO.addToSetUpdate("idTypeProduit", fournisseur.getIdTypeProduit());

            fournisseurGenericDAO.updateInDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
