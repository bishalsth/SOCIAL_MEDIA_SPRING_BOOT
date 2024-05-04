package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.exception.UserException;

import java.util.List;

public interface UserService {

    public User registerUser(User user);
    public User findUserById(Long userId) throws UserException;
    public User findUserByEmail(String email);
    public User followUser(Long reqUserId, Long userId2) throws UserException;
    public User updateUser(User user, Long userId) throws UserException;
    public List<User> searchUser(String query);
    public List<User> getAllIUser();

    public User findUserByJwt(String jwt);
}
