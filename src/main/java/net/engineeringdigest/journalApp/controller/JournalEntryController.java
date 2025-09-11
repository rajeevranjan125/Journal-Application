package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllJournalEntries() {
        return new ResponseEntity<>(journalEntryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getValueById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findByiD(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
       JournalEntry journalEntry=journalEntryService.findByiD(myId).orElse(null);
       if(journalEntry!=null){
        journalEntryService.deleteById(myId);
        return true;
       }
       return false;
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        Optional<JournalEntry> old = journalEntryService.findByiD(myId);
        if (old.isPresent()) {
            JournalEntry oldJournalEntry = old.get();
            oldJournalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle()
                    : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(
                    newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent()
                            : oldJournalEntry.getContent());
            journalEntryService.saveEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
