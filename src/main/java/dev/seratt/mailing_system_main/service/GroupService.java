package dev.seratt.mailing_system_main.service;


import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.User;

import java.util.List;
import java.util.Set;

public interface GroupService {
    public List<Group> getAllGroups();

    public void saveGroup(Group group);

    public Group getGroup(int id);

    public void deleteGroup(int id);

    public Set<Group> search(String searchText);
    Group save(Group group);

    public List<Group> getGroupsByUsersContaining(User user);
}
