package com.social.Socail.Media.repo;

import com.social.Socail.Media.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
