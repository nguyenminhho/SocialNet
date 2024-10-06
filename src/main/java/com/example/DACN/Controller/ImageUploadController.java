package com.example.DACN.Controller;//package com.Panda.API.controller;

import com.example.DACN.Entity.Image;
import com.example.DACN.Service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ImageUploadController {
    @Autowired
    CloudinaryService cloudinaryService;


    @PostMapping("/upload/image")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(cloudinaryService.uploadImage(file));
  }

    @PostMapping("/uploads")
    public ResponseEntity<List<Image>> uploadImage(
                                         @RequestParam("files") List<MultipartFile>  files
                                        ) throws Exception {
        return ResponseEntity.ok(cloudinaryService.uploadImages(files));
    }

    @PostMapping("/upload/video")
    public ResponseEntity<Image> uploadVideo(
            @RequestParam("file") MultipartFile file
                                         ) throws Exception {
        return ResponseEntity.ok(cloudinaryService.uploadVideo(file));
    }

}
//
