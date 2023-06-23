package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.form.UserForm;
import dev.seratt.mailing_system_main.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
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

    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    @GetMapping("/form")
    public String updateUser(@RequestParam("id") int id, Model model){
        User user;
        if(id == 0){
            user = new User();
            user.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            user = userService.getUser(id);
        }
        model.addAttribute("user", user);
        model.addAttribute("countryList", countryService.getAllCountries());
        return "user-form";
    }
    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") @Valid UserForm userForm, BindingResult bindingResult, Model model, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            model.addAttribute("countryList", countryService.getAllCountries());
            return "user-form";
        }

        String email = userForm.getEmail();
        User user;

        if(userForm.getId() == 0){
            if(!userService.checkEmailUniqueness(email)){
                model.addAttribute("error_message", "Email is not unique");
                model.addAttribute("countryList", countryService.getAllCountries());
                return "user-form";
            }
            user = new User();
            user.setDateOfCreation(new Timestamp(System.currentTimeMillis()));
        } else {
            user = userService.getUser(userForm.getId());
            if(!user.getEmail().equals(userForm.getEmail()) && !userService.checkEmailUniqueness(email)){
                model.addAttribute("error_message", "Email is not unique");
                model.addAttribute("countryList", countryService.getAllCountries());
                model.addAttribute("user", user);
                return "user-form";
            }
        }
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setOtchestvo(userForm.getOtchestvo());
        user.setEmail(userForm.getEmail());

        userService.getUser(userForm.getId());
        user.setCountry(countryService.findById(userForm.getCountryId()));
        user.setCity(cityService.findById(userForm.getCityId()));

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
