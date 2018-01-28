package com.github.java4wro.emailService;

import org.springframework.stereotype.Service;

@Service
public interface EmailSender {
    void sendEmail(String to, String subject, String content);
    void sendEmail(String to, String subject, String content, String attachmentPath);

}