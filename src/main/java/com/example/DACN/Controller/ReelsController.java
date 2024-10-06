package com.example.DACN.Controller;


import com.example.DACN.Dto.Request.ReelRequest;
import com.example.DACN.Dto.Response.ReelResponse;
import com.example.DACN.Service.ReelsService;
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
public class ReelsController {


    @Autowired
    ReelsService reelsService;

    @PostMapping("/reel")
    public ResponseEntity<ReelResponse> addComment(@ModelAttribute ReelRequest reelRequest,@RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(reelsService.addReels(reelRequest,file));
    }

    @GetMapping("/reel/{reelsId}")
    public ResponseEntity<ReelResponse> getComment(@PathVariable String reelsId){

        return ResponseEntity.status(HttpStatus.OK).body(reelsService.findReelsById(reelsId));
    }

    @GetMapping("/reelsById/{userId}")
    public ResponseEntity<List<ReelResponse>> getAllComment(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(reelsService.findReelsByUserId(userId));

    }

    @PutMapping("/reel/like/{reelId}")
    public ResponseEntity<ReelResponse> likedComment(@PathVariable String reelId){

        return ResponseEntity.status(HttpStatus.OK).body(reelsService.likedReels(reelId));
    }

}
