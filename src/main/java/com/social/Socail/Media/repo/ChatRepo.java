package com.social.Socail.Media.repo;

import com.social.Socail.Media.entity.Chat;
import com.social.Socail.Media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat,Long> {


    public List<Chat> findByUsersId(Long userId);


    @Query("SELECT c FROM Chat c where :user MEMBER of c.users AND :reqUser MEMBER of c.users")
    public Chat findChatByUsersId(@Param("user") User user,@Param("reqUser") User reqUser);
}
