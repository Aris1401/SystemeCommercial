package com.commercial.gestion.model;

import com.commercial.gestion.aris.bdd.annotations.ExcludeFromInsertion;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.mail.EmailService;
import com.commercial.gestion.mail.models.ReponseProformaEmail;
import com.commercial.gestion.utility.PDFGenerator;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Timestamp;

public class ReponseDemandeRecuProforma extends GenericDAO<ReponseDemandeRecuProforma>{
    @ExcludeFromInsertion
    int idReponseDemandeRecuProforma;
    Timestamp dateReponse;
    String lienEmail;
    double quantite;
    double prixUnitaire;
    int idDemandeRecuProforma;

    public int getIdReponseDemandeRecuProforma() {
        return idReponseDemandeRecuProforma;
    }

    public void setIdReponseDemandeRecuProforma(int idReponseDemandeRecuProforma) {
        this.idReponseDemandeRecuProforma = idReponseDemandeRecuProforma;
    }

    public Timestamp getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Timestamp dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getLienEmail() {
        return lienEmail;
    }

    public void setLienEmail(String lienEmail) {
        this.lienEmail = lienEmail;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdDemandeRecuProforma() {
        return idDemandeRecuProforma;
    }

    public void setIdDemandeRecuProforma(int idDemandeRecuProforma) {
        this.idDemandeRecuProforma = idDemandeRecuProforma;
    }

    public ReponseDemandeRecuProforma(){

    }

    public ReponseDemandeRecuProforma(int idReponseDemandeRecuProforma, Timestamp dateReponse, String lienEmail, int quantite, double prixUnitaire, int idDemandeRecuProforma) {
        this.setIdReponseDemandeRecuProforma(idReponseDemandeRecuProforma);
        this.setDateReponse(dateReponse);
        this.setLienEmail(lienEmail);
        this.setQuantite(quantite);
        this.setPrixUnitaire(prixUnitaire);
        this.setIdDemandeRecuProforma(idDemandeRecuProforma);
    }

    public DemandeRecuProforma getDemandeRecuFournisseurProforma()throws Exception{
        return DemandeRecuProforma.getById(this.getIdDemandeRecuProforma());
    }

    public static String getPDFDirectory() {
        return "./reponsesProformas";
    }

    public static ReponseDemandeRecuProforma repondre(DemandeRecuProforma demandeRecuProforma, double quantite, double prixUnitaire, EmailService emailService)throws Exception{
        // Insertion reponse
        ReponseDemandeRecuProforma reponse = new ReponseDemandeRecuProforma();
        reponse.setDateReponse(demandeRecuProforma.getDateDemande());
        reponse.setQuantite(quantite);
        reponse.setIdDemandeRecuProforma(demandeRecuProforma.getIdDemandeRecuProforma());
        reponse.setPrixUnitaire(prixUnitaire);

        Proforma fromReponse = Proforma.convertirDemandeRecu(reponse);
        fromReponse.setIdFournisseur(Fournisseur.getCompany().idFournisseur);

        // Creating pdf
        File pdfDirectory = new File(ReponseDemandeRecuProforma.getPDFDirectory());
        if (!pdfDirectory.exists()) pdfDirectory.mkdir();

        String fileName = new Timestamp(System.currentTimeMillis()).getTime() + ".pdf";
        File pdfFile = new File(ReponseDemandeRecuProforma.getPDFDirectory() + "/" + fileName);
        if (!pdfDirectory.exists()) pdfFile.createNewFile();

        OutputStream out = new FileOutputStream(pdfFile);
        PDFGenerator.generateProformaPDF(fromReponse, out);

        String pdfPath = pdfFile.getCanonicalPath();
        reponse.setLienEmail(pdfPath);

        reponse.insererReponse();

        // Sending email
        Fournisseur company = Fournisseur.getCompany();
        Fournisseur from = Fournisseur.getFournisseurById(demandeRecuProforma.getIdFournisseurFrom());
        ReponseProformaEmail reponseProformaEmail = new ReponseProformaEmail(company.getEmail(), from.getEmail());
        reponseProformaEmail.getMessage(company, from, reponse);
        reponseProformaEmail.setAttachement(pdfPath, fileName);

        emailService.sendMailWithAttachement(reponseProformaEmail);

        demandeRecuProforma.valider();
        return reponse;
    }

    public void insererReponse() {
        try {
            Connection c = ConnectTo.postgreS();

            int id = saveInDatabse(c);
            setIdReponseDemandeRecuProforma(id);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
