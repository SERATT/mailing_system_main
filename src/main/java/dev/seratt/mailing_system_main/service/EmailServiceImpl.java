package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.EmailDetails;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;
    public boolean sendSimpleMail(EmailDetails details) {
//        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
//            @Override
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                mimeMessage.setRecipient(Message.RecipientType.TO,
//                        new InternetAddress(details.getRecipient()));
//                mimeMessage.setFrom(new InternetAddress(sender));
//                mimeMessage.setText(details.getMsgBody());
//                mimeMessage.setSubject(details.getSubject());
//            }
//        };
        try {
//            this.javaMailSender.send(mimeMessagePreparator);
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
