package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.service.GroupService;
import dev.seratt.mailing_system_main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupRestController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public List<Group> getAllGroups(){
        List<Group> allGroups = groupService.getAllGroups();
        return allGroups;
    }

    @GetMapping("/groups/{id}")
    public Group getGroup(@PathVariable int id){
        Group group = groupService.getGroup(id);
        return group;
    }

    @PostMapping("/groups")
    public Group addNewGroup(@RequestBody Group group){
        groupService.saveGroup(group);
        return group;
    }

    @PutMapping("/groups")
    public Group updateGroup(@RequestBody Group group){
        groupService.saveGroup(group);
        return group;
    }

    @DeleteMapping("/groups/{id}")
    public String deleteGroup(@PathVariable int id){

        groupService.deleteGroup(id);
        return "Group with ID = " + id + " was deleted";

    }

    @GetMapping("/groups/search/{searchText}")
    public List<Group> searchGroups(@PathVariable String searchText){
        return groupService.search(searchText);
    }

    @GetMapping("/groups/{id}/getUsers")
    public List<User> getUsersOfGroup(@PathVariable("id") int id){
        return groupService.getGroup(id).getUsers();
    }
}
