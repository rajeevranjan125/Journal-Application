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
    public enum keys{
        WEATHER_API;
    }

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> aapCache=new HashMap<>();

    @PostConstruct
    public void init(){
      List<ConfigJournalAppEntity> all=configJournalAppRepository.findAll();
      for(ConfigJournalAppEntity configJournalAppEntity:all){
        aapCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
      }
    }
}
