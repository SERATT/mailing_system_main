package dev.seratt.mailing_system_main.controller;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.exception.UserNotFoundException;
import dev.seratt.mailing_system_main.service.GroupService;
import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.service.SpamService;
import dev.seratt.mailing_system_main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class GroupRestController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private SpamService spamService;

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
        System.out.println(groupService.getGroup(group.getId()));
        System.out.println(groupService.getGroup(group.getId()).getUsers());

        group.setUsers(groupService.getGroup(group.getId()).getUsers());
        groupService.saveGroup(group);
        return group;
    }

    @DeleteMapping("/groups/{id}")
    public String deleteGroup(@PathVariable int id){
        spamService.deleteSpamsByGroup(groupService.getGroup(id));
        groupService.deleteGroup(id);
        return "Group with ID = " + id + " was deleted";
    }

    @GetMapping("/groups/search/{searchText}")
    public Set<Group> searchGroups(@PathVariable String searchText){
        return groupService.search(searchText);
    }

    @GetMapping("/groups/{id}/users")
    public Set<User> getUsersOfGroup(@PathVariable("id") int id){
        return groupService.getGroup(id).getUsers();
    }

    @PutMapping("/groups/{groupId}/users/{userId}")
    Group addUserToGroup(@PathVariable int groupId, @PathVariable int userId){
        Group group = groupService.getGroup(groupId);
        User user = userService.getUser(userId);
        if(user == null){
            throw new UserNotFoundException("User with id " + userId + " was Not Found");
        }
        group.addUser(user);
        return groupService.save(group);
    }

    @DeleteMapping("/groups/{groupId}/users/{userId}")
    Group removeUserFromGroup(@PathVariable int groupId, @PathVariable int userId){
        Group group = groupService.getGroup(groupId);
        User user = userService.getUser(userId);
        if(user == null){
            throw new UserNotFoundException("User with id " + userId + " was Not Found");
        }
        group.removeUser(user);
        return groupService.save(group);
    }
}
