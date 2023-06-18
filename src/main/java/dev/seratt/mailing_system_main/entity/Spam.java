package dev.seratt.mailing_system_main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @Column(name = "letter_theme")
    private String letterTheme;
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
