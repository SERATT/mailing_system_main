package dev.seratt.mailing_system_main.service;


import dev.seratt.mailing_system_main.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public Set<User> search(String searchText);
}
