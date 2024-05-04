package com.social.Socail.Media.controller;

import com.social.Socail.Media.entity.Reels;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.service.ReelsService;
import com.social.Socail.Media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReelsService reelsService;

    @PostMapping("/create")
    public ResponseEntity<Reels> createReels(@RequestBody Reels reels,
                                             @RequestHeader("Authorization")String jwt){

        User reqUser = userService.findUserByJwt(jwt);
        Reels reel = reelsService.createReel(reels, reqUser);
        return new ResponseEntity<>(reel, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Reels>> getAllReel(){

        List<Reels> reel = reelsService.findAllReel();
        return new ResponseEntity<>(reel, HttpStatus.OK);
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Reels>> getUserReel(@PathVariable Long userId) throws Exception {

        List<Reels> reel = reelsService.findUserReel(userId);
        return new ResponseEntity<>(reel, HttpStatus.OK);
    }


}

