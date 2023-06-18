package dev.seratt.mailing_system_main.repository;

import dev.seratt.mailing_system_main.entity.Spam;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface SpamRepository extends JpaRepository<Spam, Integer> {
    Spam findById(int id);

    public Set<Spam> findSpamsByLetterThemeContainingIgnoreCaseOrLetterContentContainingIgnoreCase(String theme, String content);
}
