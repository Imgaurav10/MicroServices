package com.microservice.comment1.controller;

import com.microservice.comment1.entity.Comment;
import com.microservice.comment1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
       Comment newComment= commentService.saveComment(comment);
       return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    @GetMapping("{postId}")
    public List<Comment> getAllCommentsByPostId(@PathVariable("postId")String postId){
        List<Comment>comments=commentService.getAllCommentByPostId(postId);
        return comments;
    }
}
