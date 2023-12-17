package com.commercial.gestion.mail;

import com.commercial.gestion.model.ConfigurationValues;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.SearchTerm;
import jakarta.mail.search.SubjectTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public class EmailService implements IEmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendSimpleEmail(EmailDetails emailDetails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailDetails.sender);
        mailMessage.setTo(emailDetails.recipient);
        mailMessage.setText(emailDetails.msgBody);
        mailMessage.setSubject(emailDetails.subject);
        mailMessage.setCc(emailDetails.Cc);

        try {
            javaMailSender.send(mailMessage);
            return "Email envoyer.";
        } catch (Exception e) {
            return "Probleme sur envoye email: " + e.getMessage();
        }
    }

    @Override
    public String sendMailWithAttachement(EmailDetails emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailDetails.sender);
            mimeMessageHelper.setTo(emailDetails.recipient);
            mimeMessageHelper.setText(emailDetails.msgBody, true);
            mimeMessageHelper.setSubject(emailDetails.subject);

            FileSystemResource file = new FileSystemResource(emailDetails.attachement);
            mimeMessageHelper.addAttachment(emailDetails.attachementName, file);

            javaMailSender.send(mimeMessage);
            return "Email envoyer.";
        } catch (MessagingException e) {
            return "Erreur sur envoye de message.";
        }
    }

    @Override
    public Message[] fetchEmails(String subject) {
        Session session = Session.getDefaultInstance(System.getProperties(), null);

        Message[] messages = null;
        ArrayList<Message> messagesList = new ArrayList<>();
        try {
            Store store = session.getStore("imaps");
            store.connect(
                    "imap.gmail.com",
                    ConfigurationValues.getConfigurationValue("company-email"),
                    "antljhvvfjoxesep"
            );

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            SearchTerm searchTerm = new SubjectTerm(subject);

            messages = inbox.search(searchTerm);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return messages;
    }
}
