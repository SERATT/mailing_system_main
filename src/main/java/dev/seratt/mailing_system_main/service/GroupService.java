package dev.seratt.mailing_system_main.service;


import dev.seratt.mailing_system_main.entity.Group;

import java.util.List;

public interface GroupService {
    public List<Group> getAllGroups();

    public void saveGroup(Group group);

    public Group getGroup(int id);

    public void deleteGroup(int id);

    public List<Group> search(String searchText);

}
