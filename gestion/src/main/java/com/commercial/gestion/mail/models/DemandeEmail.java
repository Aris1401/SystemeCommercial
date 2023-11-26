package com.commercial.gestion.mail.models;

import com.commercial.gestion.mail.EmailDetails;
import com.commercial.gestion.model.DemandeProformaCoteFournisseur;
import com.commercial.gestion.model.Fournisseur;

public class DemandeEmail extends EmailDetails {
    public DemandeEmail(String from, String to) {
        this.sender = from;
        this.recipient = to;
        this.subject = "Demande de devis de proforma";
    }

    public void getMessage(Fournisseur from, Fournisseur to, DemandeProformaCoteFournisseur demandeProformaCoteFournisseur) {
        String htmlMessage = "";
        htmlMessage += "<div>";
            htmlMessage += "<h3>Cher(e) " + to.getNom() + ",</h3>";
            htmlMessage += "<div>";
                htmlMessage += "<p>J'espère que ce message vous trouve en bonne santé. Depuis notre compagnie " + from.getNom() + " pour une demande de proforma de ce produit suivant.</p>";
                htmlMessage += "<br />";
                htmlMessage += "<p>" + demandeProformaCoteFournisseur.obtenirArticle().getNom() + " - Quantite: " + demandeProformaCoteFournisseur.getQuantite() + " " + demandeProformaCoteFournisseur.obtenirUnite().getNom() + "</p>";
                htmlMessage += "<br />";
                htmlMessage += "<p>Merci de m'envoyer le devis proforma dès que possible.</p>";
                htmlMessage += "<br />";
                htmlMessage += "Cordialement,";
                htmlMessage += "<br /><br />";
                htmlMessage += "<p>" + from.getNom() + "</p>";
                htmlMessage += "<p>" + from.getLocalisation() + "</p>";
            htmlMessage += "</div>";
        htmlMessage += "</div>";

        this.msgBody = htmlMessage;
        this.Cc = String.valueOf(demandeProformaCoteFournisseur.getIdDemandeProformaCoteFournisseur());
    }

    public void setAttachement(String attachement, String name) {
        this.attachementName = name;
        this.attachement = attachement;
    }
}
