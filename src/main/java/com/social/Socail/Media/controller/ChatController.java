package com.social.Socail.Media.controller;

import com.social.Socail.Media.entity.Chat;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.request.CreateChatRequest;
import com.social.Socail.Media.service.ChatService;
import com.social.Socail.Media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;



    @PostMapping("/create")
    public ResponseEntity<Chat> createChat(@RequestBody CreateChatRequest chatRequest,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);

        User user2 = userService.findUserById(chatRequest.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);


    }

    @GetMapping("/get/{chatId}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);


    }
    @GetMapping("/get/user")
    public ResponseEntity<List<Chat>> getUserChat(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Chat> chat = chatService.findUserChat(user.getId());
        return new ResponseEntity<>(chat, HttpStatus.OK);


    }
}
