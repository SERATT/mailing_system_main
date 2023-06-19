package dev.seratt.mailing_system_main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spam")
public class Spam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToMany(mappedBy = "spam", cascade = CascadeType.ALL)
    Set<SentUsers> sentUsers = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @Column(name = "status_code")
    private char statusCode;

    @Column(name = "send_date")
    private Date sendDate;

    @NotBlank(message = "Theme cannot be blank")
    @Size(min = 2, max = 100, message = "Theme must be min 2 and max 100 characters")
    @Column(name = "letter_theme")
    private String letterTheme;

    @NotBlank
    @Size(min = 2, message = "Content must be min 2 characters")
    @Column(name = "letter_content")
    private String letterContent;
    public Spam() {
    }

    public Spam(int id, Group group, char statusCode, Date sendDate, String letterTheme, String letterContent) {
        this.id = id;
        this.group = group;
        this.statusCode = statusCode;
        this.sendDate = sendDate;
        this.letterTheme = letterTheme;
        this.letterContent = letterContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public char getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(char statusCode) {
        this.statusCode = statusCode;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getLetterTheme() {
        return letterTheme;
    }

    public void setLetterTheme(String letterTheme) {
        this.letterTheme = letterTheme;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    @Override
    public String toString() {
        return "Spam{" +
                "id=" + id +
                ", group=" + group +
                ", statusCode=" + statusCode +
                ", sendDate=" + sendDate +
                ", letterTheme='" + letterTheme + '\'' +
                ", letterContent='" + letterContent + '\'' +
                '}';
    }

    public Set<SentUsers> getSentUsers() {
        return sentUsers;
    }

    public void setSentUsers(Set<SentUsers> sentUsers) {
        this.sentUsers = sentUsers;
    }
}
