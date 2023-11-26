package com.commercial.gestion.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

        try {
            javaMailSender.send(mailMessage);
            return "Email envoyer.";
        } catch (Exception e) {
            return "Probleme sur envoye email: " + e.getMessage();
        }
    }

    @Override
    public String sendMailWithAttachement(EmailDetails emailDetails) {
        return null;
    }
}
