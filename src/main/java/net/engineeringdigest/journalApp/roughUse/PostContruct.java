package net.engineeringdigest.journalApp.roughUse;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PostContruct {
    private static final Logger logger = LoggerFactory.getLogger(PostContruct.class);
    
    @PostConstruct
    public void init(){
        log.info("bean is create and it is onvoked automatically");
        loadData();
    }

    private void loadData() {
      
       log.info("started loading data..");
    }
}
