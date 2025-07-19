package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.bson.types.ObjectId;

@RestController
@RequestMapping("/usersJournalEntry")
public class UserJournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> journalEntriesOfUsername(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if (journalEntries != null && !journalEntries.isEmpty()) {
            return ResponseEntity.ok(journalEntries);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> saveEntryForUsername(@RequestBody JournalEntry journalEntry,
            @PathVariable String username) {
        journalEntryService.saveEntry(journalEntry, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}{myId}")
    public ResponseEntity<?> deleteJournalEntryForUsername(@PathVariable ObjectId myId, @PathVariable String username) {
        journalEntryService.deleteById(myId, username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/id/{myId}") // there is no need to take username ,it will use further for authentication
    public ResponseEntity<?> updateJournalEntryByUsername(@RequestBody JournalEntry journalEntry, @PathVariable ObjectId myId) {
        JournalEntry oldJournalEntries=journalEntryService.findByiD(myId).orElse(null);
        if(oldJournalEntries!=null){
        oldJournalEntries.setTitle(journalEntry.getTitle()!=null && !journalEntry.getTitle().isEmpty()?journalEntry.getTitle():oldJournalEntries.getTitle());
        oldJournalEntries.setContent(journalEntry.getContent()!=null && !journalEntry.getContent().isEmpty() ? journalEntry.getContent():oldJournalEntries.getContent());
        journalEntryService.saveEntry(oldJournalEntries);
        return new ResponseEntity<>("updated sucessfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count/{username}")
    public ResponseEntity<Integer> getCountOfJournalEntry(String userName) {
        User user = userService.findByUserName(userName);
        int size = user.getJournalEntries().size();
        return ResponseEntity.ok(size);
    }

}
