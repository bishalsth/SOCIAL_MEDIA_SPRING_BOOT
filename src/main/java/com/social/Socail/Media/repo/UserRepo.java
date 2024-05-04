package com.social.Socail.Media.repo;

import com.social.Socail.Media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    public User findByEmail(String email);

    @Query("SELECT u FROM  User u where u.firstName LIKE %:query% OR  u.lastName like %:query% OR u.email LIKE %:query%")
    public List<User> searchUser( @Param("query") String query);
}
