package dev.seratt.mailing_system_main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;

import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "title")
    @NotBlank(message = "Title can not be left blank")
    @Size(min = 2, max = 25, message = "Title must be min 2 and max 25 characters")
    private String title;

    @Column(name = "description")
    @NotBlank(message = "Description can not be left blank")
    @Size(min = 2, message = "Description must be min 2 characters")
    private String description;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_and_groups",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> users;

    public Group() {
    }


    public Group(int id, String title, String description, Date dateOfCreation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getGroups().add(this);
    }
    public void removeUser(User user) {
        users.remove(user);
        user.getGroups().remove(this);
    }
}
