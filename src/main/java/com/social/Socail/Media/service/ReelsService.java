package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Reels;
import com.social.Socail.Media.entity.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reels, User user);
    public List<Reels> findAllReel();

    public List<Reels> findUserReel(Long userId) throws Exception;
}
