package com.social.Socail.Media.controller;

import com.social.Socail.Media.entity.Post;
import com.social.Socail.Media.entity.User;
import com.social.Socail.Media.exception.UserException;
import com.social.Socail.Media.response.ApiResponse;
import com.social.Socail.Media.service.PostService;
import com.social.Socail.Media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/posts/user")
    public ResponseEntity<Post> createPost(@RequestBody Post post,

                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post newPost = postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);

    }

    @DeleteMapping("/post/delete/{postId}/user")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId,
                                                  @RequestHeader("Authorization") String jwt
                                                  ) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        postService.deletPost(postId, reqUser.getId());

        ApiResponse response = new ApiResponse();
        response.setMessage("Post deleted Succesfully");
        response.setStatus(true);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId) throws Exception {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);

    }

    @GetMapping("/post/user")
    public ResponseEntity<List<Post>> findUserPosts( @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        List<Post> posts = postService.findPostByUserId(reqUser.getId());
        return new ResponseEntity<>(posts,HttpStatus.OK);

    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> allPost = postService.findAllPost();
        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }

    @PutMapping("/post/saved/{postId}/user")
    public ResponseEntity<Post> savedPost(@PathVariable Long postId,
                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.OK);

    }

    @PutMapping("/post/like/{postId}/user")
    public ResponseEntity<Post> likedPost(@PathVariable Long postId,
                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likedPost(postId, reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.OK);
    }


}
