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
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.saveEntry(user);
        return ResponseEntity.ok().build();
    }
//    @PutMapping("id/{myId}")
//    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable ObjectId myId){
//        Optional<User> old =userService.findById(myId);
//        if(old.isPresent()){
//            User oldUser=old.get();
//             oldUser.setUserName(user.getUserName()!=null && !user.getUserName().isEmpty()?user.getUserName():oldUser.getUserName());
//             oldUser.setPassword(user.getPassword()!=null && !user.getPassword().isEmpty()?user.getPassword():oldUser.getPassword());
//             return new ResponseEntity<>(oldUser,HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
   @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
//        User userInDb=userService.findByUserName(user.getUserName());
       User userInDb=userService.findByUserName(userName);
        if(userInDb!=null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
   @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        User userInDb=userService.findByUserName(user.getUserName());
        if(userInDb!=null){
           ObjectId userId= userInDb.getId();
           userService.deleteById(userId);
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
