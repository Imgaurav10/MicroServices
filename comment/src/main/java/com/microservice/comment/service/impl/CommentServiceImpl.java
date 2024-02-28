package com.microservice.comment.service.impl;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;

    @Override
    public Comment saveComment(Comment comment){

        //Check whether the post exist if yes the save the comment or else throws exception

        Post post = restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/api/posts/" + comment.getPostId(), Post.class);

        if(post!=null) {

            String radomCommentId = UUID.randomUUID().toString();
            comment.setCommentId(radomCommentId);
            comment.setPostId(post.getPostId());
            Comment savedComment = commentRepository.save(comment);
            return savedComment;
        }
        return null;
    }
    @Override
    public List<Comment> getCommentByPostId(String postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}
