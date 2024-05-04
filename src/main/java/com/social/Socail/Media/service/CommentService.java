package com.social.Socail.Media.service;

import com.social.Socail.Media.entity.Comment;

public interface CommentService {

    public Comment createComment(Comment comment,Long postId, Long userId) throws Exception;
    public Comment likeComment(Long commentId,Long userId) throws Exception;
    public Comment findCommentById(Long commentId) throws Exception;
}
