package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.*;
import dev.seratt.mailing_system_main.repository.SpamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Service
public class SpamServiceImpl implements SpamService{
    @Autowired
    SpamRepository spamRepository;
    @Autowired
    EmailService emailService;
    @Override
    public List<Spam> getAllSpams() {
        return spamRepository.findAll();
    }

    @Override
    public void saveSpam(Spam spam, Group group) {
        spam.setStatusCode('R');
        spam.setGroup(group);
        String theme = spam.getLetterTheme();
        String content = spam.getLetterContent();

        int sent = 0, not_sent = 0;
        for(User user : spam.getGroup().getUsers()){
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setSubject(theme);
            emailDetails.setMsgBody(content);

            SentUsers sentUsers = new SentUsers();
            sentUsers.setUser(user);
            sentUsers.setSpam(spam);
            sentUsers.setStatusCode('R');
            if(emailService.sendSimpleMail(emailDetails)){
                sent++;
                sentUsers.setStatusCode('G');
            } else {
                not_sent++;
            }

            spam.getSentUsers().add(sentUsers);
        }

        spam.setStatusCode('G');

        if(not_sent > 0){
            spam.setStatusCode('Y');
        }

        if(sent == 0){
            spam.setStatusCode('R');
        }
        spamRepository.save(spam);
    }

    @Override
    public Spam getSpam(int id) {
        return spamRepository.findById(id);
    }

    @Override
    public Set<Spam> search(String searchText) {
        return spamRepository.findSpamsByLetterThemeContainingIgnoreCaseOrLetterContentContainingIgnoreCase(searchText, searchText);
    }

    @Override
    public void deleteSpamsByGroup(Group group) {
        spamRepository.deleteSpamsByGroup(group);
    }


}
