package com.commercial.gestion.service;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.mail.EmailService;
import com.commercial.gestion.model.DemandeRecuProforma;
import com.commercial.gestion.model.Fournisseur;
import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DemandeRecuProformaService {
    public static final String DEMANDE_SUBJECT = "Demande de devis de proforma";

    @Autowired
    private EmailService emailService;

    public ArrayList<DemandeRecuProforma> obtenirDemandes() {
        Message[] messages = emailService.fetchEmails(DEMANDE_SUBJECT);

        ArrayList<DemandeRecuProforma> demandeRecuProformas = new ArrayList<>();
        for (Message message : messages) {
            try {
                String messageId = getMessageId(message);
                if (DemandeRecuProforma.checkIfExists(messageId)) continue;

                String emailLink = "https://mail.google.com/mail/u/0/#inbox/" + messageId;

                DemandeRecuProforma demandeRecuProforma = new DemandeRecuProforma();
                demandeRecuProforma.setDateDemande(new Timestamp(message.getSentDate().getTime()));
                demandeRecuProforma.setEtat(DemandeConfiguration.EN_COURS);
                System.out.println(message.getFrom()[0]);
                    demandeRecuProforma.setIdFournisseurFrom(Fournisseur.getFournisseurByEmail(String.valueOf(message.getFrom()[0])).getIdFournisseur());
                demandeRecuProforma.setLienEmail(messageId);

                if (message.getContent() instanceof Multipart) {
                    Multipart multipart = (Multipart) message.getContent();

                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);

                        if (bodyPart.getDisposition() != null && bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(bodyPart.getInputStream()))) {
                                // Read the header row to get column names
                                String headerLine = reader.readLine();
                                String[] columnNames = headerLine.split(",");

                                String line;
                                while ((line = reader.readLine()) != null) {
                                    // Process each line of the CSV file
                                    String[] cells = line.split(",");

                                    // Map values to columns using the header
                                    Map<String, String> rowValues = new HashMap<>();
                                    for (int j = 0; j < cells.length; j++) {
                                        String columnName = j < columnNames.length ? columnNames[j] : "Column" + j;
                                        String cellValue = cells[j];
                                        rowValues.put(columnName, cellValue);
                                    }

                                    // Demandes
//                                    private int quantite;
//                                    private int idArticle;
//                                    private int idUnite;

                                    demandeRecuProforma.setQuantite(Integer.parseInt(rowValues.get("Quantite")));
                                    demandeRecuProforma.setIdArticle(Integer.parseInt(rowValues.get("IdArticle")));
                                    demandeRecuProforma.setIdUnite(Integer.parseInt(rowValues.get("IdUnite")));
                                }
                            }
                        }
                    }
                }

                System.out.println(emailLink);
                demandeRecuProformas.add(demandeRecuProforma);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return demandeRecuProformas;
    }

    private String getMessageId(Message message) throws MessagingException {
        String[] messageIds = message.getHeader("Message-ID");
        if (messageIds != null && messageIds.length > 0) {
            return messageIds[0].replaceAll("[<>]", "");
        }
        return null;
    }
}
