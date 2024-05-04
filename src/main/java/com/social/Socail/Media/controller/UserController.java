package com.social.Socail.Media.controller;

import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.exception.UserException;
import com.social.Socail.Media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<User>  createUser(@RequestBody User user){
        User user1 = userService.registerUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @RequestHeader("Authorization") String jwt
                                           ) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user1 = userService.updateUser(user, reqUser.getId());
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @PutMapping("/follow/{userId2}")
    public ResponseEntity<User> followHandler( @RequestHeader("Authorization") String jwt,
                                              @PathVariable Long userId2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        List<User> users = userService.getAllIUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }


}
