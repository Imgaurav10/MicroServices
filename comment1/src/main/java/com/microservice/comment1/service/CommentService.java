package com.microservice.comment1.service;

import com.microservice.comment1.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(Comment comment);

    List<Comment> getAllCommentByPostId(String postId);
}
