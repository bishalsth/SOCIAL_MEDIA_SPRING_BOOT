package com.social.Socail.Media.repo;

import com.social.Socail.Media.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message,Long> {


    public List<Message> findByChatId(Long chatId);
}
