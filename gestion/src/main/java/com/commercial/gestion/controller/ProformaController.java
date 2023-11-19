package com.commercial.gestion.controller;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.model.DemandeProforma;
import com.commercial.gestion.model.Proforma;
import com.commercial.gestion.model.ProformaArticleBesoinAchat;
import com.commercial.gestion.repository.ProformaArticleBesoinAchatRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
public class ProformaController {
    private final ProformaArticleBesoinAchatRepository proformaArticleBesoinAchatRepository;

    public ProformaController(ProformaArticleBesoinAchatRepository repo) {
        this.proformaArticleBesoinAchatRepository = repo;
    }

    @PostMapping("/proformas")
    public void createProforma(@RequestBody Proforma proforma) {
        proforma.dontSave("idProforma");
        proforma.setDateObtention(new Timestamp(System.currentTimeMillis()));
        proforma.save();
    }

    @PostMapping("/proformas/demande")
    public void faireDemandeProforma(@RequestBody DemandeProforma demande) {
        demande.dontSave("idDemandeProforma");
        demande.setStatusDemande(DemandeConfiguration.EN_COURS);
        demande.setDateDemande(new Timestamp(System.currentTimeMillis()));
        demande.save();
    }

    @GetMapping("/proformas/demande/{idDemandeProforma}")
    public DemandeProforma getDemandeProforma(@PathVariable("idDemandeProforma") int idDemandeProforma) {
        return DemandeProforma.obtenirDemande(idDemandeProforma);
    }

    @Transactional
    @PostMapping("/proformas/articlebesoinachat/{idArticleBesoinAchat}")
    public ProformaArticleBesoinAchat ajouterProformaDepuisArticleBesoinAchat(@RequestBody Proforma proforma, @PathVariable("idArticleBesoinAchat") int idArticleBesoinAchat) {
        proforma.dontSave("idProforma");
        proforma.setDateObtention(new Timestamp(System.currentTimeMillis()));
        int idProforma = proforma.saveId();

        System.out.println(idArticleBesoinAchat );

        ProformaArticleBesoinAchat proformaArticleBesoinAchat = new ProformaArticleBesoinAchat();
        proformaArticleBesoinAchat.setIdProforma(idProforma);
        proformaArticleBesoinAchat.setIdArticleBesoinAchat(idArticleBesoinAchat);
        proformaArticleBesoinAchat.setDateLiaison(new Timestamp(System.currentTimeMillis()));

        proformaArticleBesoinAchat.dontSave("idProformaBesoinAchat");

        proformaArticleBesoinAchat.save();

        return null;
    }

    @GetMapping("proformas/demande/valider/{idDemandeProforma}")
    public void validerDemandeProforma(@PathVariable("idDemandeProforma") int idDemandeProforma) {
        DemandeProforma.validerDemandeProforma(idDemandeProforma);
    }

    @GetMapping("proformas/articlebesoinachat/{idArticleBesoinAchat}")
    public ArrayList<Proforma> getProformaArticleBesoinAchat(@PathVariable("idArticleBesoinAchat") int idArticleBesoinAchat) {
        return Proforma.obtenirProformaBesoinArticle(idArticleBesoinAchat);
    }
    @GetMapping("/proformas/fournisseur/{idFournisseur}/ArticleBesoinAchat/{idArticleBesoinAchat}")
    public DemandeProforma checkDemandeProforma(@PathVariable("idFournisseur") int idFournisseur, @PathVariable("idArticleBesoinAchat") int idArticleBesoinAchat) {
        return DemandeProforma.demandeFournisseurArticleBesoinAchat(idFournisseur, idArticleBesoinAchat);
    }
}
