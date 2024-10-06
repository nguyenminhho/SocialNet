package com.example.DACN.Service;


import com.example.DACN.Dto.Request.StoryRequest;
import com.example.DACN.Dto.Response.StoryResponse;
import com.example.DACN.Entity.Story;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.StoryMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {

    @Autowired
    UserService userService;

    @Autowired
    StoryRepository storyRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StoryMapper storyMapper;

    @Autowired
    CloudinaryService cloudinaryService;


    public StoryResponse addStory(StoryRequest storyRequest, MultipartFile file) throws IOException {
        Story savedStory = storyMapper.toStory(storyRequest);
        User user = userMapper.toUser(userService.getMyInfo());

        savedStory.setCreatedAt(LocalDateTime.now());
        savedStory.setImage(cloudinaryService.uploadVideo(file));
        savedStory.setUser(user);
        savedStory.setLiked(new ArrayList<>());


        return storyMapper.toStoryResponse(storyRepository.save(savedStory));
    }


    public StoryResponse findStoryById(String storyId) {
        return storyMapper.toStoryResponse(storyRepository.findById(storyId).get());
    }


    public List<StoryResponse> findStoryByUserId(String userId) {

        return storyRepository.findStoryByUserId(userId).stream().map(s -> storyMapper.toStoryResponse(s)).toList();
    }

    public boolean deleteStory(String storyId) {
        Story s = storyRepository.findById(storyId).get();
        storyRepository.delete(s);
        return true;
    }


    public StoryResponse likedStory(String storyId) {
        Story savedStory = storyRepository.findById(storyId).get();
        User user = userMapper.toUser(userService.getMyInfo());

        if(savedStory.getLiked().contains(user)){
            savedStory.getLiked().remove(user);
        }else  savedStory.getLiked().add(user);

        return storyMapper.toStoryResponse(storyRepository.save(savedStory));
    }
}
