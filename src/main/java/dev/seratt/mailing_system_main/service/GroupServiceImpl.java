package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public Group getGroup(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Set<Group> search(String searchText) {
        return groupRepository.
                findGroupsByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase
                        (searchText, searchText);
    }

    @Override
    public Set<User> getUsersByGroupId(int groupId) {
        Group group = groupRepository.findById(groupId);
        return group.getUsers();
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }
}
