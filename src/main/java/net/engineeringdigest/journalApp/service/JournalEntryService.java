package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
//    public void saveEntry(JournalEntry journalEntry, String userName){
//        User user=userService.findByUserName(userName);
//        journalEntry.setDate(LocalDateTime.now());
//        JournalEntry saved=journalEntryRepository.save(journalEntry);
//        user.getJournalEntries().add(saved);
//        userService.saveEntry(user);
//    }
    @Transactional //made transactional context corresponding to this method  ,if any operation fails then all operation rollbacks - (achieved Isolation, Atomicity )
    public void saveEntry(JournalEntry journalEntries,String userName){
        try{
            journalEntries.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEntries);
            User user=userService.findByUserName(userName);
            user.getJournalEntries().add(saved);
            user.setUserName(null);
            userService.saveEntry(user);
        }catch(Exception e){
            System.out.println(e);
            throw  new RuntimeException("An error occurred while saving entry"+e);
        }
    }
    public void saveEntry(JournalEntry journalEntires){
        journalEntryRepository.save(journalEntires);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findByiD(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }
    public void deleteById(ObjectId myId, String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }
    public long countJournalEntries(){
       return journalEntryRepository.count();

    }
}
