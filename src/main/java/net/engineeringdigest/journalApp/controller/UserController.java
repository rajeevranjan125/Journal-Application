package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.security.core.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

   @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
       User userInDb=userService.findByUserName(userName);
        if(userInDb!=null) { 
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
   @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userInDb=userService.findByUserName(userName);
        if(userInDb!=null){
           ObjectId userId= userInDb.getId();
           userService.deleteById(userId);
           return new ResponseEntity<>(HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
