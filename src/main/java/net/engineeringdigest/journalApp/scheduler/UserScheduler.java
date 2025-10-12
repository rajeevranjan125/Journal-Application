package net.engineeringdigest.journalApp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.controller.UserRepositoryImpl;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentAnalysis;

public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private SentimentAnalysis sentimentAnalysis;
    @Autowired
    private AppCache appCache;

    //@Scheduled(cron="0 0 9 ? * SUN *") 
    @Scheduled(cron="0 0/1 * 1/1 * ? *") 
    public void fetchUsersAndSendSAMail() {
     List<User> users=  userRepositoryImpl.getUserForSA();
     for(User user:users){
      List<JournalEntry> jorunalntries=  user.getJournalEntries();
      List<String> filteredEntries = jorunalntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7,ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
      String entryin=String.join(" ", filteredEntries);
      String sentimnet=sentimentAnalysis.getSentiment(entryin);
      emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", sentimnet);
     }
    }

    @Scheduled(cron="0 0/5 * 1/1 * ? *") 
    public void clearAppCache(){
        appCache.init();
    }
}
