package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Chat;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepo chatRepo;


    @Override
    public Chat createChat(User reqUser, User user2) {

        Chat isChatExist = chatRepo.findChatByUsersId(user2, reqUser);
        if(isChatExist !=null){
            return isChatExist;
        }
        Chat chat= new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamps(LocalDateTime.now());

        return chatRepo.save(chat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {
        Optional<Chat> optChat = chatRepo.findById(chatId);
        if(optChat.isEmpty()){
            throw new Exception("Chat is not found");
        }

        return optChat.get();
    }

    @Override
    public List<Chat> findUserChat(Long userId) {

        return chatRepo.findByUsersId(userId);

    }
}
