package dev.seratt.mailing_system_main;

import dev.seratt.mailing_system_main.exception_handler.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Handler.class)
public class MailingSystemMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingSystemMainApplication.class, args);
    }

}
