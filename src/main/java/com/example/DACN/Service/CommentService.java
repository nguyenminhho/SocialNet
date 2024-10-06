package com.example.DACN.Service;


import com.example.DACN.Dto.Request.CommentRequest;
import com.example.DACN.Dto.Response.CommentResponse;
import com.example.DACN.Entity.Comment;
import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.CommentMapper;
import com.example.DACN.Mapper.PostMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.CommentRepository;
import com.example.DACN.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService{
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;


    public CommentResponse addComment(CommentRequest commentRequest, String postId) {
        User user = userMapper.toUser(userService.getMyInfo());
        Post post = postMapper.toPost(postService.findPostById(postId));

        Comment savedComment = commentMapper.toComment(commentRequest);

        savedComment.setUser(user);
        savedComment.setCreatedAt(LocalDateTime.now());
        savedComment.setLiked(new ArrayList<>());

        var comment = commentRepository.save(savedComment);
        post.getComment().add(savedComment);
        postRepository.save(post);

        return commentMapper.toCommentResponse(comment);
    }

    public CommentResponse findCommentById(String postId) {
        return commentMapper.toCommentResponse(commentRepository.findById(postId).get());
    }

    public List<CommentResponse> findCommentByUserId(String userId) {
        return commentRepository.findAllCommentByUserId(userId).stream().map(c -> commentMapper.toCommentResponse(c)).toList();
    }


    public CommentResponse likedComment(String commentId) {
        User user = userMapper.toUser(userService.getMyInfo());
        Comment comment = commentMapper.toComment(findCommentById(commentId));
        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else  comment.getLiked().remove(user);

        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }
}
