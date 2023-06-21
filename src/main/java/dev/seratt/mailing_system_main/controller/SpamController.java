package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.communication.GroupCommunication;
import dev.seratt.mailing_system_main.communication.SpamCommunication;
import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.Spam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/spam")
public class SpamController {
    @Autowired
    SpamCommunication spamCommunication;
    @Autowired
    GroupCommunication groupCommunication;

    private int groupId;

    @GetMapping("/create")
    public String goToChooseGroup(Model model){
        List<Group> groupsList = groupCommunication.getAllGroups();
        model.addAttribute("groupsList", groupsList);
        return "choose-group";
    }

    @GetMapping("/send_mail")
    public String sendEmail(@RequestParam("groupId") int groupId, Model model){
        Spam spam = new Spam();
        spam.setSendDate(new Date(System.currentTimeMillis()));
        model.addAttribute("spam", spam);
        this.groupId = groupId;
        return "spam-form";
    }

    @PostMapping("/save")
    public String saveSpam(@ModelAttribute @Valid Spam spam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "spam-form";
        }
        spamCommunication.saveSpam(spam, this.groupId);
        return "redirect:/mailing";
    }
}
