package com.commercial.gestion.controller;

import com.commercial.gestion.model.BonDeCommande;
import com.commercial.gestion.model.BonDeCommandeEnvoie;
import com.commercial.gestion.model.ModeDePaiement;
import com.commercial.gestion.model.Utilisateur;
import com.commercial.gestion.repository.ModeDePaiementRepository;
import com.commercial.gestion.response.ValidationBonDeCommandeResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @GetMapping("/bondecommandes/{idBonDeCommande}/valider")
    public ValidationBonDeCommandeResponse validerBonDeCommande(@PathVariable("idBonDeCommande") int idBonDeCommande, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        BonDeCommande bonDeCommande = BonDeCommande.obtenirBonDeCommandeAvec(idBonDeCommande);

        ValidationBonDeCommandeResponse response = new ValidationBonDeCommandeResponse();
        response.addToData(bonDeCommande);
        try {
            bonDeCommande.valider(user);
        } catch (Exception e) {
            response.setErrorMessage(e.getMessage());
        }

        return response;
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

    @PostMapping("/bondecommandes/{bondecommande}/fournisseur/{fournisseur}/envoie")
    public boolean envoyerBonDeCommande(HttpSession session,@PathVariable("bondecommande") int idBonDeCommande, @PathVariable("fournisseur") int idFournisseur)throws Exception{
        return BonDeCommandeEnvoie.envoyerBonDeCommandeVersFournisseur((Utilisateur)session.getAttribute("user"),idBonDeCommande,idFournisseur);
    }
}
