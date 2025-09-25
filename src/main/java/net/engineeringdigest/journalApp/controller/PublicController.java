package net.engineeringdigest.journalApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        String userName = user.getUserName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb == null) {
            userService.saveNewEntry(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Duplicate users are not allowed");
    }

    @GetMapping("all-users")
    public ResponseEntity<?> getUser() {
        List<User> user = userService.findAll();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "all-ok";
    }

}
