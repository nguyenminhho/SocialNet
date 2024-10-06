package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.StoryRequest;
import com.example.DACN.Dto.Response.StoryResponse;
import com.example.DACN.Service.StoryService;
import com.example.DACN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StoryController {

    @Autowired
    UserService userService;
    @Autowired
    StoryService storyService;


    @PostMapping("/story/add")
    public ResponseEntity<StoryResponse> addComment(@ModelAttribute StoryRequest storyRequest,@RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(storyService.addStory(storyRequest, file));
    }

    @GetMapping("/story/{storyId}")
    public ResponseEntity<StoryResponse> getStory(@PathVariable String  storyId){
        return ResponseEntity.status(HttpStatus.OK).body(storyService.findStoryById(storyId));
    }

    @DeleteMapping("/story/delete/{storyId}")
    public ResponseEntity<Boolean> deleteStory(@PathVariable String  storyId){
        return ResponseEntity.status(HttpStatus.OK).body(storyService.deleteStory(storyId));
    }

    @GetMapping("/storyById/{userId}")
    public ResponseEntity<List<StoryResponse>> getAllStory(@PathVariable String userId){

        return ResponseEntity.status(HttpStatus.OK).body(storyService.findStoryByUserId(userId));

    }

    @PutMapping("/story/like/{storyId}")
    public ResponseEntity<StoryResponse> likedComment(@PathVariable String storyId){

        return ResponseEntity.status(HttpStatus.OK).body(storyService.likedStory(storyId));
    }

}
