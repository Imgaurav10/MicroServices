package com.microservice.post1.service.impl;

import com.microservice.post1.config.RestTemplateConfig;
import com.microservice.post1.entity.Post;
import com.microservice.post1.payload.PostDto;
import com.microservice.post1.repository.PostRepository;
import com.microservice.post1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private RestTemplateConfig restTemplateConfig;
    @Override
    public Post save(Post post) {
       String postId=UUID.randomUUID().toString();
       post.setId(postId);
        Post saved=postRepo.save(post);
        return saved;
    }

    @Override
    public Post findById(String id) {
       Post post= postRepo.findById(id).get();
        return post;
    }

    @Override
    public PostDto getPostWithComment(String postId) {
        Post post = postRepo.findById(postId).get();
        ArrayList comments = restTemplateConfig.getRestTemplates().getForObject("http://localhost:8082/api/comments/" + postId, ArrayList.class);
        PostDto postDto= new PostDto();
       postDto.setPostId(post.getId());
       postDto.setTitle(post.getTitle());
       postDto.setDescription(post.getDescription());
       postDto.setContent(post.getContent());
       postDto.setComments(comments);
        return postDto;
    }
}
