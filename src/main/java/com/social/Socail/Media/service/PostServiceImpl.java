package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Post;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.exception.PostException;
import com.social.Socail.Media.exception.UserException;
import com.social.Socail.Media.repo.PostRepo;
import com.social.Socail.Media.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @Override
    public Post createNewPost(Post post, Long userId) throws UserException {
        User user = userService.findUserById(userId);;

        Post post1= new Post();
        post1.setCaption(post.getCaption());
        post1.setImage(post.getImage());
        post1.setVideo(post.getVideo());
        post1.setUser(user);
        post1.setCreatedAt(LocalDateTime.now());
        return postRepo.save(post1);
    }

    @Override
    public void deletPost(Long postId, Long userId) throws UserException {
        User user = userService.findUserById(userId);

        Post post = findPostById(postId);

        if(post.getUser().getId() != user.getId()){
            throw  new UserException("You cannot delete other posts");
        }

        postRepo.delete(post);


    }

    @Override
    public Post findPostById(Long postId) throws UserException {
        Optional<Post> optionalPost = postRepo.findById(postId);
        if(optionalPost.isEmpty()){
            throw new UserException("Post not found with id"+postId);
        }
        return optionalPost.get();
    }

    @Override
    public List<Post> findPostByUserId(Long userId) {

        List<Post> posts = postRepo.findByUserId(userId);
        return posts;
    }

    @Override
    public List<Post> findAllPost() {
        return postRepo.findAll();

    }

    @Override
    public Post savedPost(Long postId, Long userId) throws UserException {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else
            user.getSavedPost().add(post);

        userRepo.save(user);
        return post;
    }

    @Override
    public Post likedPost(Long postId, Long userId) throws UserException {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }

        return postRepo.save(post);
    }
}
