package dev.seratt.mailing_system_main.repository;

import dev.seratt.mailing_system_main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Set<User>
        findUsersByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrOtchestvoContainingIgnoreCaseOrEmailContainingIgnoreCase
        (String name, String surname, String otchestvo, String email);

    public User findById(int id);

    public User findUserByEmail(String email);


}
