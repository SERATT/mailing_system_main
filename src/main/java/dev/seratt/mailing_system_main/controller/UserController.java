package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.service.GroupService;
import dev.seratt.mailing_system_main.service.SpamService;
import dev.seratt.mailing_system_main.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SpamService spamService;

    @GetMapping("/save_update")
    public String updateUser(@RequestParam("id") int id, Model model){
        User user;
        if(id == 0){
            user = new User();
            user.setDateOfCreation(new Date(System.currentTimeMillis()));
        } else {
            user = userService.getUser(id);
        }
        model.addAttribute("user", user);
        return "user-form";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user-form";
        }
        String email = user.getEmail();
        if(!userService.checkEmailUniqueness(email)){
            model.addAttribute("email_error_message", "Email is not unique");
            return "user-form";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id){
        List<Group> groupsContainingUser = groupService.getGroupsByUsersContaining(userService.getUser(id));
        groupsContainingUser.forEach(group -> group.removeUser(userService.getUser(id)));
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

}
