package com.commercial.gestion.mail.models;

import com.commercial.gestion.mail.EmailDetails;
import com.commercial.gestion.model.DemandeProformaCoteFournisseur;
import com.commercial.gestion.model.Fournisseur;
import com.commercial.gestion.model.ReponseDemandeRecuProforma;

public class ReponseProformaEmail extends EmailDetails {
    public ReponseProformaEmail(String from, String to) {
        this.sender = from;
        this.recipient = to;
        this.subject = "Demande de devis de proforma";
    }

    public void getMessage(Fournisseur from, Fournisseur to, ReponseDemandeRecuProforma reponseDemandeRecuProforma) {
        String htmlMessage = "";
        htmlMessage += "<div>";
        htmlMessage += "<h3>Cher(e) " + to.getNom() + ",</h3>";
        htmlMessage += "<div>";
        htmlMessage += "<p>Nous vous remercions de l'intérêt que vous portez à nos produits/services et de votre demande de proforma.<br/>Suite à votre demande, veuillez trouver ci-dessous les détails de la proforma pour votre référence :</p>";
        htmlMessage += "Cordialement,";
        htmlMessage += "<br /><br />";
        htmlMessage += "<p>" + from.getNom() + "</p>";
        htmlMessage += "<p>" + from.getEmail() + "</p>";
        htmlMessage += "</div>";
        htmlMessage += "</div>";

        this.msgBody = htmlMessage;
        this.Cc = String.valueOf(reponseDemandeRecuProforma.getIdDemandeRecuProforma());
    }

    public void setAttachement(String pdf, String nom) {
        this.attachement = pdf;
        this.attachementName = nom;
    }
}
