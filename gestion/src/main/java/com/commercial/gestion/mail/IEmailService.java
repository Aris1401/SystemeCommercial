package com.commercial.gestion.mail;

public interface IEmailService {
    String sendSimpleEmail(EmailDetails emailDetails);
    String sendMailWithAttachement(EmailDetails emailDetails);
}
