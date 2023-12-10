package com.commercial.gestion.controller;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.mail.EmailService;
import com.commercial.gestion.model.*;
import com.commercial.gestion.model.dto.QuantitePrixUnitaireDTO;
import com.commercial.gestion.repository.ProformaArticleBesoinAchatRepository;
import com.commercial.gestion.utility.PDFGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

@RestController
public class ProformaController {

    @Autowired
    private EmailService emailService;

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
        proforma.setIdUnite(1);
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
        ArrayList<Proforma> proformas = Proforma.obtenirProformaBesoinArticle(idArticleBesoinAchat);
        proformas.sort(Comparator.comparingDouble(proforma -> proforma.getPrixUnitaire()));
        return proformas;
    }
    @GetMapping("/proformas/fournisseur/{idFournisseur}/ArticleBesoinAchat/{idArticleBesoinAchat}")
    public DemandeProforma checkDemandeProforma(@PathVariable("idFournisseur") int idFournisseur, @PathVariable("idArticleBesoinAchat") int idArticleBesoinAchat) {
        return DemandeProforma.demandeFournisseurArticleBesoinAchat(idFournisseur, idArticleBesoinAchat);
    }

    @GetMapping("/proformas/{proforma}/pdf")
    public void getProformaPDF(HttpServletResponse response, @PathVariable("proforma") int idProforma) {
        Proforma proforma = Proforma.obtenirById(idProforma);

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        try {
            PDFGenerator.generateProformaPDF(proforma, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/proformas/demandes/reponse/{demande}")
    public void repondreDemandeRecu(@PathVariable("demande") int idDemandeRecu, @RequestBody QuantitePrixUnitaireDTO quantitePrixUnitaireDTO) {
        try {
            DemandeRecuProforma demandeRecuProforma = DemandeRecuProforma.getById(idDemandeRecu);

            ReponseDemandeRecuProforma.repondre(demandeRecuProforma, quantitePrixUnitaireDTO.quantite, quantitePrixUnitaireDTO.prixUnitaire, emailService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
