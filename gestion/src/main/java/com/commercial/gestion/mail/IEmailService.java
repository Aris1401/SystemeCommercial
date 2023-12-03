package com.commercial.gestion.mail;

import jakarta.mail.Message;

public interface IEmailService {
    String sendSimpleEmail(EmailDetails emailDetails);
    String sendMailWithAttachement(EmailDetails emailDetails);
    public Message[] fetchEmails(String subject);
}
