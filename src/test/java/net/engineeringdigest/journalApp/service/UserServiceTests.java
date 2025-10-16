package net.engineeringdigest.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserService userService; 
    @Test
    public void testAdd(){
        assertEquals(4, 4+0);
    }
    @Disabled
    @Test
    public void testFindByUsername(){
        assertNotNull(userRepository.findByUserName("ram") );
        // assertNull(userRepository.findByUserName("ram") );
        // assertTrue(5>6);
        // assertFalse(8==9);
    }
    @Disabled 
    @Test
    public void testjournalEntriesOfUsername(){
        User user=userRepository.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    } 
    @ParameterizedTest
    @CsvSource({
        "1,3,4",
        "2,1,3",
        "5,4,9"
    })
    public void testSum(int a,int b,int expected){
        assertEquals(expected, a+b);
    }
    // @ParameterizedTest
    // @ValueSource(strings={ //@EnumSource({}) //@ValueSource(ints= {}) //ArgumentsSource 
    //     "ram",
    //     "mridhul",
    //     "lakshman",
    //     "rahul"
    // })
    // @ParameterizedTest
    // @ArgumentsSource(UserArgumentsProvider.class)
    // public void testSaveNewUser(User user){
    //   assertTrue(userService.saveNewEntry(user));  
    // }
    //  @BeforeEach
    // public void set(){ 
    //     //when you want to test before running each test then use @BeforeEach annotation.
    // }
    // @BeforeAll - used to run before all methods tests.
    // @AfterAll - used to run after all moethods tests.
}  
 