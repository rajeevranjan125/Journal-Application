package net.engineeringdigest.journalApp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;


public class UserDetailsServiceImplTest {
    @InjectMocks
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    // @Test
    // public void loadByUserNameTest(String userName) {
    //     when(userRepository.findByUserName(ArgumentMatchers.anyString()))
    //             .thenReturn(User.builder().userName("abc").password("abc").roles(new ArrayList<>()).build());
    //     UserDetails user = userDetailsServiceImpl.loadUserByUsername(userName);
    //     Assertions.assertNotNull(user);
    // }
}
