package com.commercial.gestion.controller.fournisseur;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.mail.EmailService;
import com.commercial.gestion.mail.models.DemandeEmail;
import com.commercial.gestion.model.DemandeProformaCoteFournisseur;
import com.commercial.gestion.model.Fournisseur;
import com.commercial.gestion.response.Response;
import com.commercial.gestion.utility.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@RestController
public class DemandeProfromaCoteFournisseurController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/demandeproforma/fournisseur")
    public DemandeProformaCoteFournisseur faireUneDemandeDeProforma(@RequestBody DemandeProformaCoteFournisseur demande) {
        demande.setDateDemande(new Timestamp(System.currentTimeMillis()));
        demande.setEtat(DemandeConfiguration.EN_COURS);
        Response<DemandeProformaCoteFournisseur> demandeProformaCoteFournisseurResponse = new Response<>();

        // Generating csv file
        File csvDirectory = new File(DemandeProformaCoteFournisseur.CSV_SAVE_PATH);
        if (!csvDirectory.exists()) csvDirectory.mkdir();

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String fileName = currentTime.getTime() + ".csv";
        File csvGenerated = new File(DemandeProformaCoteFournisseur.CSV_SAVE_PATH + "/" + fileName);
        if (!csvGenerated.exists()) {
            try {
                csvGenerated.createNewFile();
                demande.setLienEmail(csvGenerated.getCanonicalPath());
                CSVWriter.generateCSV(csvGenerated, new Object[]{ demande });

                demande.inserer();

                // EMail
                Fournisseur from = Fournisseur.getFournisseurById(1);
                Fournisseur to = Fournisseur.getFournisseurById(demande.getIdFournisseur());

                DemandeEmail demandeEmail = new DemandeEmail(from.getEmail(), "cest.arisaina@gmail.com");
                demandeEmail.getMessage(from, to, demande);
                demandeEmail.setAttachement(csvGenerated.getCanonicalPath(), fileName);

                emailService.sendMailWithAttachement(demandeEmail);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



        return null;
    }
}
