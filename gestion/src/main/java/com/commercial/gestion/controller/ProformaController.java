package com.commercial.gestion.controller;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.model.DemandeProforma;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class ProformaController {
    @PostMapping("/proformas/demande")
    public void faireDemandeProforma(@RequestBody DemandeProforma demande) {
        demande.dontSave("idDemandeProforma");
        demande.setStatusDemande(DemandeConfiguration.EN_COURS);
        demande.setDateDemande(new Timestamp(System.currentTimeMillis()));
        demande.save();
    }

    public void 
}
