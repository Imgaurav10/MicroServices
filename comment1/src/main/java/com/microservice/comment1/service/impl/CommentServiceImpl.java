package com.microservice.comment1.service.impl;

import com.microservice.comment1.config.RestTemplateConfig;
import com.microservice.comment1.entity.Comment;
import com.microservice.comment1.payload.Post;
import com.microservice.comment1.repository.CommentRepository;
import com.microservice.comment1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplateConfig;
    @Override
    public Comment saveComment(Comment comment) {
        Post post = restTemplateConfig.getRestTemplates().getForObject("http://localhost:8081/api/posts/" + comment.getPostId(), Post.class);
        if (post!=null){
           String commentId= UUID.randomUUID().toString();
           comment.setId(commentId);
           Comment saved=commentRepository.save(comment);
           return saved;
        }else {
            return null;
        }

    }

    @Override
    public List<Comment> getAllCommentByPostId(String postId) {
        List<Comment> comments=commentRepository.findByPostId(postId);
        return comments;
    }
}
