package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/usersJournalEntry")
public class UserJournalEntryController {
    private static final Logger logger = LoggerFactory.getLogger(UserJournalEntryController.class);

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> journalEntriesOfUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null && !journalEntries.isEmpty()) {
            return ResponseEntity.ok(journalEntries);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntry = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!journalEntry.isEmpty()) {
            return ResponseEntity.ok(journalEntry);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> saveEntryForUsername(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.saveEntry(journalEntry, userName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryForUsername(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntry = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!journalEntry.isEmpty()) {
            journalEntryService.deleteById(myId, userName);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJournalEntryByUsername(@RequestBody JournalEntry journalEntry,
            @PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> listOfOldJournalEntries = user.getJournalEntries().stream()
                .filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!listOfOldJournalEntries.isEmpty()) {
            JournalEntry oldJournalEntries = journalEntryService.findByiD(myId).orElse(null);
            oldJournalEntries.setTitle(
                    journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty() ? journalEntry.getTitle()
                            : oldJournalEntries.getTitle());
            oldJournalEntries.setContent(journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()
                    ? journalEntry.getContent()
                    : oldJournalEntries.getContent());
            journalEntryService.saveEntry(oldJournalEntries);
            return new ResponseEntity<>("updated sucessfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
