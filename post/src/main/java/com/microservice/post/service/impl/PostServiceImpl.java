package com.microservice.post.service.impl;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostServiceImpl  {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplateConfig restTemplate;


    public Post savePost(Post post){
        String randomPostId = UUID.randomUUID().toString();
        post.setId(randomPostId);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    public Post getPostById(String postId) {
        Post post  = postRepository.findById(postId).get();
        return post;
    }

    public PostDto getAllCommentsForParticularPost(String postId) {
        ArrayList comments = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/" + postId, ArrayList.class);

        Post post = getPostById(postId);

        PostDto dto = new PostDto();

        dto.setPostId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setComments(comments);
        return dto;
    }
}
