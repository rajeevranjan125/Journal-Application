package net.engineeringdigest.journalApp.controller;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping
    public List<JournalEntry> getAll(){
       return journalEntryService.getAll();
    }
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return  myEntry;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getValueById(@PathVariable ObjectId myId){
        return journalEntryService.findByiD(myId).orElse(null);
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
         return true;
    }
    @PutMapping("id/{myId}")
    public JournalEntry updateEntry(@RequestBody JournalEntry newEntry, @PathVariable ObjectId myId){
        JournalEntry old=journalEntryService.findByiD(myId).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
    @GetMapping("count")
    public int getCoutOfJournalEntry(){
        return 0;
    }
}
