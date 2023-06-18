package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.Spam;
import dev.seratt.mailing_system_main.repository.SpamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SpamServiceImpl implements SpamService{
    @Autowired
    SpamRepository spamRepository;
    @Override
    public List<Spam> getAllSpams() {
        return spamRepository.findAll();
    }

    @Override
    public void saveSpam(Spam spam) {
        spamRepository.save(spam);
    }

    @Override
    public Spam getSpam(int id) {
        return spamRepository.findById(id);
    }

    @Override
    public Set<Spam> search(String searchText) {
        return spamRepository.findSpamsByLetterThemeContainingIgnoreCaseOrLetterContentContainingIgnoreCase(searchText, searchText);
    }
}
