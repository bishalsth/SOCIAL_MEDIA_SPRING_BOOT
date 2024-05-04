package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Comment;
import com.social.Socail.Media.entity.Post;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.repo.CommentRepo;
import com.social.Socail.Media.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepo postRepo;
    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception {

        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setContent(comment.getContent());
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepo.save(comment);

        post.getComments().add(savedComment);
        postRepo.save(post);


        return savedComment;
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);

        }else {
            comment.getLiked().remove(user);
        }
        return comment;
    }

    @Override
    public Comment findCommentById(Long commentId) throws Exception {
        Optional<Comment> optComment = commentRepo.findById(commentId);
        if(optComment.isEmpty()){
            throw new Exception("Comment not exist");
        }

        return optComment.get();
    }
}
