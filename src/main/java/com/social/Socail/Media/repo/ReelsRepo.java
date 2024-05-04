package com.social.Socail.Media.repo;

import com.social.Socail.Media.entity.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepo extends JpaRepository<Reels, Long> {

    public List<Reels> findByUserId(Long userId);
}
