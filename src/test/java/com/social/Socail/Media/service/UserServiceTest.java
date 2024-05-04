package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.exception.UserException;
import com.social.Socail.Media.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup(){

    }

    @Test
    public void testRegisterUser(){
//        Arrange
        User user = new User();
        user.setFirstName("bishal");
        user.setLastName("Shrestha");
        user.setPassword("1232545");
        user.setEmail("bishl@gmail.com");


        when (userRepo.save(any (User.class))).thenReturn(user);

//        ACT

        User savedUser = userService.registerUser(user);

//Assert
        assertEquals(user.getFirstName(),savedUser.getFirstName());
        assertEquals(user.getLastName(),savedUser.getLastName());
        assertEquals(user.getEmail(),savedUser.getEmail());
        assertEquals(user.getPassword(),savedUser.getPassword());

        verify(userRepo, times(1)).save(any(User.class));

    }

    @Test(expected = UserException.class)
    public void testfindUserById_WhenUserNotExists() throws UserException{

//     Arrange
        Long userId = 1L;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

//        Act
        userService.findUserById(userId);

        verify(userRepo, times(1)).findById(userId);

    }

    @Test
    public void testFindUserByEmail(){

        String email ="abcd@gmail.com";
        User user = new User();
        user.setFirstName("bishal");
        user.setLastName("shrestha");
        user.setEmail(email);

        when(userRepo.findByEmail(email)).thenReturn(user);

        User foundUser = userService.findUserByEmail(email);

        assertEquals(email,foundUser.getEmail());
        verify(userRepo,times(1)).findByEmail(email);
    }


    @Test
    public void testFollowUser() throws UserException{


    }
}
