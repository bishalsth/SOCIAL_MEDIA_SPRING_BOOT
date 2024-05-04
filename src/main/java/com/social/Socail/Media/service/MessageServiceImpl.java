package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Chat;
import com.social.Socail.Media.entity.Message;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.repo.ChatRepo;
import com.social.Socail.Media.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepo  chatRepo;

    @Override
    public Message createMessage(User user, Long chatId, Message req) throws Exception {
//        User user = userService.findUserById(userId);
        Chat chat = chatService.findChatById(chatId);

        Message message = new Message();
        message.setContent(req.getContent());
        message.setChat(chat);
        message.setUser(user);
        message.setImage(req.getImage());
        message.setTimestamps(LocalDateTime.now());

        Message savedMessage = messageRepo.save(message);
        chat.getMessages().add(savedMessage);
        chatRepo.save(chat);

        return savedMessage;


    }

    @Override
    public List<Message> findChatMessage(Long chatId) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        return messageRepo.findByChatId(chat.getId());
    }
}
