package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.EmailDetails;

public interface EmailService {
    boolean sendSimpleMail(EmailDetails details);
}
