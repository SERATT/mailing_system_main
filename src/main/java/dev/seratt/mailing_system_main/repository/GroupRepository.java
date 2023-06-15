package dev.seratt.mailing_system_main.repository;

import dev.seratt.mailing_system_main.entity.Group;
import dev.seratt.mailing_system_main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Integer> {
//    public List<Employee> getAllEmployees();
//
//    public void saveEmployee(Employee employee);
//
//    public Employee getEmployee(int id);
//
//    public void deleteEmployee(int id);
    public Set<Group> findGroupsByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    public Group findById(int id);

//    @Query(value = "select user from users join (select * from users_and_groups where group_id = ?1) as joint",
//    nativeQuery = true)
//    List<User> findUsersOfGroup(int groupId);
}
