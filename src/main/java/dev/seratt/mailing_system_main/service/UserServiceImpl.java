package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.User;
import dev.seratt.mailing_system_main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> search(String searchText){
        return userRepository
                .findUsersByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrOtchestvoContainingIgnoreCaseOrCountryContainingIgnoreCaseOrCityContainingIgnoreCase(
                    searchText, searchText, searchText, searchText, searchText
                );
    }
}
