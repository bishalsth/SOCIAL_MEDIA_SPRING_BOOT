package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Reels;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.repo.ReelsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelServiceImpl implements ReelsService{

    @Autowired
    private UserService userService;

    @Autowired
    private ReelsRepo reelsRepo;

    @Override
    public Reels createReel(Reels reels, User user) {

        reels.setTitle(reels.getTitle());
        reels.setUser(user);
        reels.setVideo(reels.getVideo());
        return reelsRepo.save(reels);

    }

    @Override
    public List<Reels> findAllReel() {
        return reelsRepo.findAll();
    }

    @Override
    public List<Reels> findUserReel(Long userId) throws Exception {
        User user = userService.findUserById(userId);


        return reelsRepo.findByUserId(userId);
    }
}
