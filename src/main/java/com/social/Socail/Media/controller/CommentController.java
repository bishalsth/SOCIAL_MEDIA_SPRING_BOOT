package com.social.Socail.Media.controller;

import com.social.Socail.Media.entity.Comment;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.service.CommentService;
import com.social.Socail.Media.service.PostService;
import com.social.Socail.Media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private PostService postService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> createComment(@RequestHeader("Authorization")String jwt,
                                                 @RequestBody Comment comment,
                                                 @PathVariable Long postId) throws Exception {


        User user = userService.findUserByJwt(jwt);
        Comment comments = commentService.createComment(comment, postId, user.getId());
        return  new ResponseEntity<>(comments, HttpStatus.CREATED);

    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> createComment(@RequestHeader("Authorization")String jwt,

                                                 @PathVariable Long commentId) throws Exception {


        User user = userService.findUserByJwt(jwt);
        Comment comments = commentService.likeComment(commentId,user.getId());
        return  new ResponseEntity<>(comments, HttpStatus.OK);

    }
}
