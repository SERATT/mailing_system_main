package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.Spam;
import dev.seratt.mailing_system_main.entity.User;
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
    public Spam addNewSpam(@RequestBody Spam spam, @PathVariable("group_id") int id) {
        spam.setStatusCode('R');
        spam.setGroup(groupService.getGroup(id));
        System.out.println(spam);
        spamService.saveSpam(spam);
        return spam;
    }

    @GetMapping("/spam/search/{searchText}")
    public Set<Spam> searchSpams(@PathVariable String searchText){
        return spamService.search(searchText);
    }


}
