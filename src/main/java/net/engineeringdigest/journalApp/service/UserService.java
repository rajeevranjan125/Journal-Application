package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void saveEntry(User user){
        userRepository.save(user);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
    public long countOfUsers(){
        return userRepository.count();
    }
}
