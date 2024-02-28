package com.microservice.post1.controller;

import com.microservice.post1.config.RestTemplateConfig;
import com.microservice.post1.entity.Post;
import com.microservice.post1.payload.PostDto;
import com.microservice.post1.repository.PostRepository;
import com.microservice.post1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService  postService;


    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        Post post1=postService.save(post);
        return new ResponseEntity<>(post1, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable("id")String id ){
       Post post= postService.findById(id);
       return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("{postId}/comments")
    public PostDto getPostWithComments(@PathVariable("postId")String postId){
       PostDto postDto= postService.getPostWithComment(postId);
       return postDto;
    }
}
