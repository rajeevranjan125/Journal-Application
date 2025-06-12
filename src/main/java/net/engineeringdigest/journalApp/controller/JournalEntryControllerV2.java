package net.engineeringdigest.journalApp.controller;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user=userService.findByUserName(userName);
        List <JournalEntry> journalEntries=user.getJournalEntries();
//      List <JournalEntry> journalEntries = journalEntryService.getAll();
      if(journalEntries!=null && !journalEntries.isEmpty() ){
          return ResponseEntity.ok(journalEntries);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    @PostMapping
//    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
//        myEntry.setDate(LocalDateTime.now());
//        journalEntryService.saveEntry(myEntry);
//        return  myEntry;
//    }
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
       try{
           journalEntryService.saveEntry(myEntry,userName);
           return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
       }catch(Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
//    @GetMapping("id/{myId}")
//    public JournalEntry getValueById(@PathVariable ObjectId myId){
//        return journalEntryService.findByiD(myId).orElse(null);
//    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getValueById(@PathVariable ObjectId myId){
        Optional <JournalEntry> journalEntry=journalEntryService.findByiD(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity <>(journalEntry.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @DeleteMapping("id/{myId}")
//    public boolean deleteEntryById(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//         return true;
//    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){ //? is the wildcard pattern
       journalEntryService.deleteById(myId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @PutMapping("id/{myId}")
//    public JournalEntry updateEntry(@RequestBody JournalEntry newEntry, @PathVariable ObjectId myId){
//        JournalEntry old=journalEntryService.findByiD(myId).orElse(null);
//        if(old!=null){
//            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ?newEntry.getTitle():old.getTitle());
//            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
//        }
//        journalEntryService.saveEntry(old);
//        return old;
//    }
//    actual method

//    @PutMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
//        Optional<JournalEntry> old=journalEntryService.findByiD(myId);
//        if(old.isPresent()){
//           JournalEntry oldJournalEntry=old.get();
//           oldJournalEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty()?newEntry.getTitle():oldJournalEntry.getTitle());
//           oldJournalEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty()?newEntry.getContent():oldJournalEntry.getContent());
//           journalEntryService.saveEntry(oldJournalEntry);
//           return new ResponseEntity<>(oldJournalEntry,HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @GetMapping("count")
//    public int getCoutOfJournalEntry(){
//        return 0;
//    }
    @GetMapping("count")
    public ResponseEntity <Long> getCountOfJournalEntry(){
       long count= journalEntryService.countJournalEntries();
       return new ResponseEntity<>(count,HttpStatus.OK);
    }
}
