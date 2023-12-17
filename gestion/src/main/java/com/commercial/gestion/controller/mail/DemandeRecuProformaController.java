package com.commercial.gestion.controller.mail;

import com.commercial.gestion.mail.EmailService;
import com.commercial.gestion.model.DemandeRecuProforma;
import com.commercial.gestion.service.DemandeRecuProformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DemandeRecuProformaController {
    @Autowired
    private DemandeRecuProformaService demandeRecuProformaService;

    @GetMapping("/updateinbox")
    public void fetchEmails() {
        ArrayList<DemandeRecuProforma> demandeRecuProformas = demandeRecuProformaService.obtenirDemandes();
        for (DemandeRecuProforma demandeRecuProforma : demandeRecuProformas) {
            try {
                demandeRecuProforma.inserer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/demandesrecuproformas")
    public ArrayList<DemandeRecuProforma> getOpenDemandes() {
        try {
            return DemandeRecuProforma.getAllDemandesRecuOuvertes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
