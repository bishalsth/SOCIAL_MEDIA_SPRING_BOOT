package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Message;
import com.social.Socail.Media.entity.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Long chatId, Message req) throws Exception;

    public List<Message> findChatMessage(Long chatId) throws Exception;
}
