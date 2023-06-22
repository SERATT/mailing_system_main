package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.Spam;

import java.util.List;
import java.util.Set;

public interface SpamService {
    public List<Spam> getAllSpams();

    public void saveSpam(Spam spam, Group group);

    public Spam getSpam(int id);

    public Set<Spam> search(String searchText);

    public void deleteSpamsByGroup(Group group);

}
