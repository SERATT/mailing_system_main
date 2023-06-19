package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.communication.UserCommunication;
import dev.seratt.mailing_system_main.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserCommunication userCommunication;

    @GetMapping("/save_update")
    public String updateUser(@RequestParam("id") int id, Model model){
        User user;
        if(id == 0){
            user = new User();
            user.setDateOfCreation(new Date(System.currentTimeMillis()));
        } else {
            user = userCommunication.getUser(id);
        }
        model.addAttribute("user", user);
        return "user-form";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user-form";
        }
        userCommunication.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id){
        userCommunication.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user){
        userCommunication.saveUser(user);
        return "redirect:/users";
    }

}
