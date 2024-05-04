package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Post;
import com.social.Socail.Media.exception.PostException;
import com.social.Socail.Media.exception.UserException;

import java.util.List;

public interface PostService {

    public Post createNewPost(Post post, Long userId) throws Exception;
    public void deletPost(Long postId, Long userId) throws Exception;
    public Post findPostById(Long postId) throws Exception;
    public List<Post> findPostByUserId(Long userId);
    public List<Post> findAllPost();
    public Post savedPost(Long postId,Long userId) throws Exception;
    public Post likedPost(Long postId,Long userId) throws Exception;
}
