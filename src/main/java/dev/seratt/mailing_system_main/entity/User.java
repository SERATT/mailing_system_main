package dev.seratt.mailing_system_main.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String surname;

    @Column(name = "otchestvo")
    @NotBlank(message = "Otchestvo can not be empty")
    @Size(min = 2, max = 25, message = "min 2 and max 25 characters")
    private String otchestvo;

    @Column(name = "country")
    @NotBlank(message = "Country can not be empty")
    @Size(min = 2, max = 25, message = "Country min 2 and max 25 characters")
    private String country;


    @Column(name = "city")
    @NotBlank(message = "City can not be empty")
    @Size(min = 2, max = 25, message = "City min 2 and max 25 characters")
    private String city;

    @Size(max = 100, message = "Email max 100 characters")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid email: does not match the pattern")
    @Column(name = "email")
    private String email;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Group> groups = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<SentUsers> sentUsers;

    public User() {
    }

    public User(int id, String name, String surname, String otchestvo, String country, String city, String email, Date dateOfCreation) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.otchestvo = otchestvo;
        this.country = country;
        this.city = city;
        this.email = email;
        this.dateOfCreation = dateOfCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date date_of_creation) {
        this.dateOfCreation = date_of_creation;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User: " +
                "id=" + id +
                ", email=" + email;
    }
}
