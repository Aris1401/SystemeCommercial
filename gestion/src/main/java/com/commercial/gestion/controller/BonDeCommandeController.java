package com.commercial.gestion.controller;

import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.model.BonDeCommande;
import com.commercial.gestion.model.ModeDePaiement;
import com.commercial.gestion.repository.ModeDePaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class BonDeCommandeController {
    private final ModeDePaiementRepository modeDePaiementRepository;

    @Autowired
    public BonDeCommandeController(ModeDePaiementRepository repository) {
        this.modeDePaiementRepository = repository;
    }

    @GetMapping("/bondecommandes")
    public ArrayList<BonDeCommande> obtenirBonDeCommandes() {
        return BonDeCommande.obtenirBonDeCommandeNonValide();
    }

    @GetMapping("/bondecommandes/{idBonDeCommande}")
    public BonDeCommande obtenirBonDeCommande(@PathVariable("idBonDeCommande") int idBonDeCommande) {
        return BonDeCommande.obtenirBonDeCommandeAvec(idBonDeCommande);
    }

    @GetMapping("/modesdepaiement")
    public Iterable<ModeDePaiement> modesDePaiement() {
        return ModeDePaiement.allModesDePaiement();
    }

    @GetMapping("/bondecommandes/{idBonDeCommande}/modedepaiment")
    public void ajouterModeDePaiement(@RequestParam("idModeDePaiement") int idModeDePaiement, @PathVariable("idBonDeCommande") int idBonDeCommande) {
        BonDeCommande bonDeCommande = BonDeCommande.obtenirBonDeCommandeAvec(idBonDeCommande);
        bonDeCommande.ajouterModeDePaiement(idModeDePaiement);
    }

    @GetMapping("/bondecommandes/{idBonDeCommande}/conditiondepaiement")
    public void ajouterConditionDePaiement(@RequestParam("conditionDePaiement") String conditionDePaiement, @PathVariable("idBonDeCommande") int idBonDeCommande) {
        BonDeCommande bonDeCommande = BonDeCommande.obtenirBonDeCommandeAvec(idBonDeCommande);
        bonDeCommande.ajouterConditionDePaiement(conditionDePaiement);
    }

    @GetMapping("/bondecommandes/{idBonDeCommande}/datelivraison")
    public void ajouterDateDeLivraison(@RequestParam("dateLivraison") String dateLivraison, @PathVariable("idBonDeCommande") int idBonDeCommande) {
        BonDeCommande bonDeCommande = BonDeCommande.obtenirBonDeCommandeAvec(idBonDeCommande);
        bonDeCommande.ajouterDateDeLivraison(Timestamp.valueOf(LocalDateTime.parse(dateLivraison)));
    }
}
