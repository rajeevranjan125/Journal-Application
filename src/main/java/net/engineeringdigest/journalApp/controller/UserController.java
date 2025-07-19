package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.saveEntry(user);
        return ResponseEntity.ok().build();
    }

   @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
       User userInDb=userService.findByUserName(userName);
        if(userInDb!=null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
   @DeleteMapping("{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        User userInDb=userService.findByUserName(username);
        if(userInDb!=null){
           ObjectId userId= userInDb.getId();
           userService.deleteById(userId);
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
   @GetMapping("/count")
   public ResponseEntity<?> countOfUsers(){
   return ResponseEntity.ok( userService.countOfUsers());
   }
}
