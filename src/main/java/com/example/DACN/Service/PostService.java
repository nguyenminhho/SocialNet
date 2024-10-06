package com.example.DACN.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.DACN.Dto.Request.PostRequest;
import com.example.DACN.Dto.Response.PostResponse;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.PostMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.PostRepository;
import com.example.DACN.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService{
    @Autowired
    UserService userService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PostMapper postMapper;

    public PostResponse createPost(PostRequest postRequest, List<MultipartFile> files) throws Exception {
        User user = userMapper.toUser(userService.getMyInfo());
        Post savedPost = postMapper.toPost(postRequest);


        savedPost.setImages(cloudinaryService.uploadImages(files));
        savedPost.setUser(user);
        savedPost.setLiked( new ArrayList<>());
        savedPost.setComment( new ArrayList<>());
        savedPost.setCreatedAt(LocalDateTime.now());

        return postMapper.toPostResponse(postRepository.save(savedPost));
    }

    public boolean deletedPost(String postId) throws Exception {
        Post post = postRepository.findById(postId).get();
        User user = userMapper.toUser(userService.getMyInfo());

        if(post.getUser() != user){
            throw new Exception("User don't have this post");
        }
        postRepository.delete(post);
        return true;
    }


    public List<PostResponse> findAllPostByUserId(String userId) {
        return postRepository.findAllPostByUserId(userId).stream().map(p->postMapper.toPostResponse(p)).toList();
    }


    public PostResponse findPostById(String postId) {
        return postMapper.toPostResponse(postRepository.findById(postId).get());
    }


    public List<PostResponse> findAllPost() {
        return postRepository.findAll().stream().map(p -> postMapper.toPostResponse(p)).toList();
    }

    public String savedPost(String postId) {
        Post post = postMapper.toPost(findPostById(postId));
        User user = userMapper.toUser(userService.getMyInfo());
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
            userRepository.save(user);
            return "Da xoa post khoi muc luu";
        }else {
            user.getSavedPost().add(post);
            userRepository.save(user);
            return "Da luu post vao muc luu";
        }

    }

    public PostResponse likedPost(String postId) {
        Post post = postMapper.toPost(findPostById(postId));
        User user = userMapper.toUser(userService.getMyInfo());

        if (post.getLiked() == null) {
            post.setLiked(new ArrayList<>()); // Initialize if it's null
        }

        if (post.getLiked().contains(user)) {
            System.out.println("1");
            post.getLiked().remove(user); // Remove the user from liked
        } else {
            System.out.println("2");
            post.getLiked().add(user); // Add the user to liked
        }

        return postMapper.toPostResponse(postRepository.save(post));
    }

}
