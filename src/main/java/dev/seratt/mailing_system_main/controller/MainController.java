package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.service.GroupService;
import dev.seratt.mailing_system_main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String mainPage(){
        return "main-page";
    }

    @GetMapping("/users")
    public String usersPage(Model model){
        model.addAttribute("usersList", userService.getAllUsers());
        return "users-page";
    }

    @GetMapping("/searchUsers")
    public String findUsersBySearch(@RequestParam("searchText") String searchText,
                                    Model model){
        if(searchText.isEmpty()){
            return "redirect:/users";
        }
        List<User> usersList = userService.search(searchText);
        model.addAttribute("usersList", usersList);
        return "users-page";
    }

    @GetMapping("/groups")
    public String groupsPage(Model model){
        model.addAttribute("groupsList", groupService.getAllGroups());
        return "groups-page";
    }

    @GetMapping("/searchGroups")
    public String findGroupsBySearch(@RequestParam("searchText") String searchText,
                                     Model model){
        if(searchText.isEmpty()){
            return "redirect:/groups";
        }
        List<Group> groupsList = groupService.search(searchText);
        model.addAttribute("groupsList", groupsList);
        return "groups-page";
    }
}
