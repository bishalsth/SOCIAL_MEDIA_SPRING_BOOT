package com.social.Socail.Media.repo;

import com.social.Socail.Media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    @Query("SELECT p from Post p where p.user.id=:userId")
    public List<Post> findByUserId(Long userId);
}
