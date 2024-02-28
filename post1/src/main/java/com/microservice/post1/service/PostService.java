package com.microservice.post1.service;

import com.microservice.post1.entity.Post;
import com.microservice.post1.payload.PostDto;

public interface PostService {
    Post save(Post post);

    Post findById(String id);

    PostDto getPostWithComment(String postId);
}
