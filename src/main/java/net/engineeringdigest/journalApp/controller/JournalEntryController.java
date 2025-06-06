package net.engineeringdigest.journalApp.controller;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journallocal")
public class JournalEntryController {
    private  Map<ObjectId,JournalEntry> journalEntries=new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll(){
        return  new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return  true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getValueById(@PathVariable long myId){
         return journalEntries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable long myId){
         journalEntries.remove(myId);
         return true;
    }
    @PutMapping("id/{myId}")
    public boolean updateEntry(@RequestBody JournalEntry myEntry, @PathVariable long myId){
       journalEntries.put(myEntry.getId(), myEntry);
       return true;
    }
    @GetMapping("count")
    public int getCoutOfJournalEntry(){
        return journalEntries.size();
    }
}
