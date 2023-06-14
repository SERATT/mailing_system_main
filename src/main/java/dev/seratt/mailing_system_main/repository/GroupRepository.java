package dev.seratt.mailing_system_main.repository;

import dev.seratt.mailing_system_main.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
//    public List<Employee> getAllEmployees();
//
//    public void saveEmployee(Employee employee);
//
//    public Employee getEmployee(int id);
//
//    public void deleteEmployee(int id);
    public List<Group> findGroupsByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    public Group findById(int id);
}
