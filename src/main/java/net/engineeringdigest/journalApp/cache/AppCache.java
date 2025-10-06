package net.engineeringdigest.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;

@Component
public class AppCache {

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> AAP_CACHE=new HashMap<>();

    @PostConstruct
    public void init(){
      List<ConfigJournalAppEntity> all=configJournalAppRepository.findAll();
      for(ConfigJournalAppEntity configJournalAppEntity:all){
        AAP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
      }
    }
}
