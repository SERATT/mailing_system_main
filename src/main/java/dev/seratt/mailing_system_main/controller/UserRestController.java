package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){

        userService.deleteUser(id);
        return "User with ID = " + id + " was deleted";

    }

    @GetMapping("/users/search/{searchText}")
    public List<User> searchUsers(@PathVariable String searchText){
        return userService.search(searchText);
    }
}
