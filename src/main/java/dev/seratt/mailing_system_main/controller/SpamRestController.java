package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.*;
import dev.seratt.mailing_system_main.service.EmailService;
import dev.seratt.mailing_system_main.service.GroupService;
import dev.seratt.mailing_system_main.service.SpamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class SpamRestController {
    @Autowired
    SpamService spamService;
    @Autowired
    GroupService groupService;
    @Autowired
    EmailService emailService;
    @GetMapping("/spam")
    public List<Spam> getAllSpam(){
        List<Spam> spamsList = spamService.getAllSpams();
        return spamsList;
    }

    @GetMapping("/spam/{id}")
    public Spam getSpam(@PathVariable("id") int id){
        return spamService.getSpam(id);
    }

    @PostMapping("/spam/group/{group_id}")
    public void addNewSpam(@RequestBody Spam spam, @PathVariable("group_id") int id) {
        spam.setStatusCode('R');
        spam.setGroup(groupService.getGroup(id));
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
        spamService.saveSpam(spam);
    }

    @GetMapping("/spam/search/{searchText}")
    public Set<Spam> searchSpams(@PathVariable String searchText){
        return spamService.search(searchText);
    }


}
