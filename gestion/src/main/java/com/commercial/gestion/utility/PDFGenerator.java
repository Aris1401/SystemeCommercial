package com.commercial.gestion.utility;

import com.commercial.gestion.model.Fournisseur;
import com.commercial.gestion.model.Proforma;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class PDFGenerator {
    public static void generateProformaPDF(Proforma proforma, OutputStream out) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(20);

        Paragraph titleParagraph = new Paragraph("FACTURE PROFORMA", fontTitle);
        titleParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(titleParagraph);

        Font fontHeading = FontFactory.getFont(FontFactory.HELVETICA);
        fontHeading.setSize(16);

        Font fontRegular = FontFactory.getFont(FontFactory.HELVETICA);
        fontRegular.setSize(8);

        // Details founiseeur
        Fournisseur fournisseur = proforma.getFournisseur();
        Paragraph detailsFournisseur = new Paragraph();

        Paragraph nomFournisseur = new Paragraph("Fournisseur: " + fournisseur.getNom(), fontHeading);
        detailsFournisseur.add(nomFournisseur);

        Paragraph localisationFournisseur = new Paragraph(fournisseur.getAddresse() + ", " + fournisseur.getLocalisation(), fontRegular);
        detailsFournisseur.add(localisationFournisseur);

        Paragraph emailFournisseur = new Paragraph("Email: " + fournisseur.getEmail(), fontRegular);
        detailsFournisseur.add(emailFournisseur);

        Paragraph contactFournisseur = new Paragraph("Contact: " + fournisseur.getContact(), fontRegular);
        detailsFournisseur.add(contactFournisseur);

        detailsFournisseur.setSpacingAfter(25f);

        // Table
        PdfPTable detailsProduitTable = new PdfPTable(5);
        detailsProduitTable.setWidthPercentage(100f);
        detailsProduitTable.setWidths(new float[]{20f, 20f, 20f, 20f, 20f});
        detailsProduitTable.setSpacingBefore(5);

        PdfPCell headingCell = new PdfPCell();
        headingCell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
        headingCell.setPadding(5f);

        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA);
        headingFont.setColor(CMYKColor.WHITE);

        headingCell.setPhrase(new Phrase("Article", headingFont));
        detailsProduitTable.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Quantite", headingFont));
        detailsProduitTable.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Unite", headingFont));
        detailsProduitTable.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Prix Unitaire", headingFont));
        detailsProduitTable.addCell(headingCell);
        headingCell.setPhrase(new Phrase("Montant", headingFont));
        detailsProduitTable.addCell(headingCell);

        // Data
        detailsProduitTable.addCell(proforma.getArticle().getNom());
        detailsProduitTable.addCell(String.valueOf(proforma.getQuantite()));
        detailsProduitTable.addCell(proforma.getUnite().getNom());
        detailsProduitTable.addCell(String.valueOf(proforma.getPrixUnitaire()) + " Ar");
        detailsProduitTable.addCell(String.valueOf(proforma.getMontant()) + " Ar");

        // Informations
        Paragraph footerParagraph = new Paragraph();
        footerParagraph.setSpacingBefore(25f);
        footerParagraph.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph montantParagraph = new Paragraph("Montant HT: " + proforma.getMontant() + " Ar", fontRegular);
        montantParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
        Paragraph tvaParagraph = new Paragraph("TVA: " + proforma.getTVA() + " Ar", fontRegular);
        tvaParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
        Paragraph montantTTC = new Paragraph("TTC: " + proforma.getMontantTTC() + " Ar", fontRegular);
        montantTTC.setAlignment(Paragraph.ALIGN_RIGHT);

        Paragraph signature = new Paragraph("Signature", fontRegular);
        signature.setSpacingBefore(25f);
        signature.setAlignment(Paragraph.ALIGN_RIGHT);

        footerParagraph.add(montantParagraph);
        footerParagraph.add(tvaParagraph);
        footerParagraph.add(montantTTC);

        document.add(detailsFournisseur);
        document.add(detailsProduitTable);
        document.add(footerParagraph);
        document.add(signature);

        document.close();
    }
}
