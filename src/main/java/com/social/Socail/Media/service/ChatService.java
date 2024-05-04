package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Chat;
import com.social.Socail.Media.entity.User;

import java.util.List;

public interface ChatService {


    public Chat createChat(User reqUser, User user2);
    public Chat findChatById(Long chatId) throws Exception;

    public List<Chat> findUserChat(Long userId);
}
