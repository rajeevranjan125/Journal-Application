package net.engineeringdigest.journalApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GT650 {
    @Autowired
    private Bike bike;
    @GetMapping("/start")
    public String GT650Start(){
        return bike.start();
    }
}
