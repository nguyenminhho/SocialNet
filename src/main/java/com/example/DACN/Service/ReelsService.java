package com.example.DACN.Service;


import com.example.DACN.Dto.Request.ReelRequest;
import com.example.DACN.Dto.Response.ReelResponse;
import com.example.DACN.Entity.Reels;
import com.example.DACN.Entity.Story;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.ReelMapper;
import com.example.DACN.Mapper.StoryMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.ReelRepository;
import com.example.DACN.Repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReelsService{

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    @Autowired
    ReelMapper reelMapper;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ReelRepository reelRepository;


    public ReelResponse addReels(ReelRequest reelRequest, MultipartFile file) throws IOException {
        Reels savedReels = reelMapper.toReels(reelRequest);
        User user = userMapper.toUser(userService.getMyInfo());

        savedReels.setVideo(cloudinaryService.uploadImage(file));
        savedReels.setLiked(new ArrayList<>());
        savedReels.setUser(user);
        savedReels.setCreatedAt(LocalDateTime.now());

        return reelMapper.toReelResponse(reelRepository.save(savedReels));
    }


    public ReelResponse findReelsById(String reelsId) {
        return  reelMapper.toReelResponse(reelRepository.findById(reelsId).get());
    }


    public List<ReelResponse> findReelsByUserId(String userId) {
        return reelRepository.findReelsByUserId(userId).stream().map(r -> reelMapper.toReelResponse(r)).toList();
    }


    public ReelResponse likedReels(String reelsId) {
        Reels reels = reelRepository.findById(reelsId).get();
        User user = userMapper.toUser(userService.getMyInfo());

        if(reels.getLiked().contains(user)){
            reels.getLiked().remove(user);
        }else  reels.getLiked().add(user);

        return reelMapper.toReelResponse(reelRepository.save(reels));
    }
}
