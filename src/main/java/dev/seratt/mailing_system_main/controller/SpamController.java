package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.communication.GroupCommunication;
import dev.seratt.mailing_system_main.communication.SpamCommunication;
import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.Spam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/spam")
public class SpamController {
    @Autowired
    SpamCommunication spamCommunication;
    @Autowired
    GroupCommunication groupCommunication;

    private int groupId;

    @GetMapping("/save_update")
    public String goSpamForm(@RequestParam("id") int id, Model model){
        Spam spam = new Spam();
        spam.setSendDate(new Date(System.currentTimeMillis()));
        model.addAttribute("spam", spam);
        return "spam-form";
    }

    @GetMapping("/send_mail")
    public String sendEmail(@RequestParam("id") int groupId, Model model){
        Spam spam = new Spam();
        spam.setSendDate(new Date(System.currentTimeMillis()));
        model.addAttribute("spam", spam);
        this.groupId = groupId;
        return "spam-form";
    }

    @PostMapping("/save")
    public String saveSpam(@ModelAttribute Spam spam){
        spamCommunication.saveSpam(spam, this.groupId);
        return "redirect:/mailing";
    }
}
