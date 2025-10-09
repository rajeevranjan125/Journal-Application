package net.engineeringdigest.journalApp.controller;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import net.engineeringdigest.journalApp.entity.User;

@RequestMapping("/check-sa-users")
@RestController
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<User> getUserForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        // query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        // Criteria criteria=new Criteria(); 
        // query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),Criteria.where("sentimentAnalysis").is(true)));
        List<User> users= mongoTemplate.find(query,User.class );
        return users;
    }
}
