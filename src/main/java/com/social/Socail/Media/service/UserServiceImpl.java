package com.social.Socail.Media.service;

import com.social.Socail.Media.config.JwtProvider;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.exception.UserException;
import com.social.Socail.Media.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepo.save(newUser);


    }

    @Override
    public User findUserById(Long userId) throws UserException {

        Optional<User> optUser = userRepo.findById(userId);
        if(optUser.isEmpty()){
            throw new UserException("user not found with id" +userId);
        }

       return optUser.get();

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);

    }

    @Override
    public User followUser(Long reqUserId, Long userId2) throws UserException {

        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowing().add(user2.getId());

        userRepo.save(reqUser);
        userRepo.save(user2);


        return reqUser;
    }

    @Override
    public User updateUser(User user, Long userId) throws UserException {

        User user1 = findUserById(userId);

        if(user.getFirstName() !=null){
            user1.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null){
            user1.setLastName(user.getLastName());
        }
        if(user.getEmail() !=null){
            user1.setEmail(user.getEmail());
        }
        if(user.getPassword() !=null){
            user1.setPassword(user.getPassword());
        }
         return userRepo.save(user1);



    }

    @Override
    public List<User> searchUser(String query) {
        return userRepo.searchUser(query);

    }

    @Override
    public List<User> getAllIUser() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepo.findByEmail(email);
        return user;
    }
}
