package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class JournalEntryService {
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntries, String username) {

        User user = userService.findByUserName(username);
        journalEntries.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntries);
        user.getJournalEntries().add(journalEntries);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntires) {
        journalEntryRepository.save(journalEntires);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findByiD(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    public void deleteById(ObjectId myIde) {
        journalEntryRepository.deleteById(myIde);
    }

    public void deleteById(ObjectId myId, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }

    public long countJournalEntries() {
        return journalEntryRepository.count();
    }
}
