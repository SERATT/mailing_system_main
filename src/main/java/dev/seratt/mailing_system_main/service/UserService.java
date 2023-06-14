package dev.seratt.mailing_system_main.service;


import dev.seratt.mailing_system_main.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public List<User> search(String searchText);
}
