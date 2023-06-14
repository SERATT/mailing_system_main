package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.communication.GroupCommunication;
import dev.seratt.mailing_system_main.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    GroupCommunication groupCommunication;
    @GetMapping("/save_update")
    public String updateGroup(@RequestParam("id") int id, Model model){
        Group group;
        if(id == 0){
            group = new Group();
            group.setDateOfCreation(new Date(System.currentTimeMillis()));
        } else {
            group = groupCommunication.getGroup(id);
        }
        model.addAttribute("group", group);
        return "group-form";
    }
    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("group") Group group){
        groupCommunication.saveGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/delete")
    public String deleteGroup(@RequestParam("id") int id){
        groupCommunication.deleteGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("/createGroup")
    public String createGroup(@RequestBody Group group){
        groupCommunication.saveGroup(group);
        return "redirect:/groups";
    }


}
