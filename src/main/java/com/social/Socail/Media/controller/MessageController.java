package com.social.Socail.Media.controller;

import com.social.Socail.Media.entity.Message;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.service.MessageService;
import com.social.Socail.Media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    @PostMapping("/create/{chatId}")
    public ResponseEntity<Message> createMessage(@RequestHeader("Authorization")String jwt,
                                                 @RequestBody Message message,
                                                 @PathVariable Long chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message1 = messageService.createMessage(user, chatId, message);
        return new ResponseEntity<>(message1, HttpStatus.CREATED);

    }

    @GetMapping("/get/{chatId}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable Long chatId) throws Exception {

        List<Message> chatMessage = messageService.findChatMessage(chatId);
        return new ResponseEntity<>(chatMessage, HttpStatus.OK);

    }
}
