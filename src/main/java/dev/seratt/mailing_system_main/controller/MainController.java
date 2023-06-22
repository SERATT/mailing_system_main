package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.*;
import dev.seratt.mailing_system_main.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;
    @Autowired
    SpamService spamService;
    @Autowired
    CountryService countryService;
    @Autowired
    CityService cityService;
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
                                    Model model, HttpServletRequest request){
        if(searchText.isEmpty()){
            return "redirect:/users";
        }
        Set<User> usersList = userService.search(searchText);
        model.addAttribute("usersList", usersList);
        return "users-page";
    }

    @GetMapping("/searchUsersForAdding")
    public String findUsersForAddingBySearch(@RequestParam("searchText") String searchText,
                                             Model model, HttpServletRequest request, @RequestParam("groupId") int groupId){
        if(searchText.isEmpty()){
            return "redirect:" + request.getHeader("Referer");
        }
        Set<User> usersList = userService.search(searchText);
        model.addAttribute("usersList", usersList);
        model.addAttribute("groupId", groupId);
        return "choose-user";
    }

    @GetMapping("/searchGroupsForAdding")
    public String findGroupsForAddingBySearch(@RequestParam("searchText") String searchText,
                                             Model model, HttpServletRequest request){
        if(searchText.isEmpty()){
            return "redirect:" + request.getHeader("Referer");
        }
        Set<Group> groupsList = groupService.search(searchText);
        model.addAttribute("groupsList", groupsList);
        return "choose-group";
    }

    @GetMapping("/groups")
    public String groupsPage(Model model){
        model.addAttribute("groupsList", groupService.getAllGroups());
        return "groups-page";
    }

    @GetMapping("/mailing")
    public String mailingPage(Model model){
        model.addAttribute("spamsList", spamService.getAllSpams());
        return "mailing-page";
    }

    @GetMapping("/searchGroups")
    public String findGroupsBySearch(@RequestParam("searchText") String searchText,
                                     Model model){
        if(searchText.isEmpty()){
            return "redirect:/groups";
        }
        Set<Group> groupsList = groupService.search(searchText);
        model.addAttribute("groupsList", groupsList);
        return "groups-page";
    }

    @GetMapping("/searchSpams")
    public String findSpamsBySearch(@RequestParam("searchText") String searchText,
                                     Model model){
        if(searchText.isEmpty()){
            return "redirect:/mailing";
        }
        Set<Spam> spamsList = spamService.search(searchText);
        model.addAttribute("spamsList", spamsList);
        return "mailing-page";
    }


}
