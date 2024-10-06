package com.example.DACN.Controller;


import com.example.DACN.Dto.Request.PostRequest;
import com.example.DACN.Dto.Response.PostResponse;
import com.example.DACN.Mapper.PostMapper;
import com.example.DACN.Service.PostService;
import com.example.DACN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*import org.springframework.web.multipart.MultipartFile;*/


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/post/add")
    public ResponseEntity<PostResponse> createdPost(@ModelAttribute PostRequest postRequest, @RequestParam("files") List<MultipartFile> files) throws Exception {
System.out.println(postRequest);
        return ResponseEntity.status(HttpStatus.OK).body(postService.createPost(postRequest,files));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Boolean> deletedPost(@PathVariable("postId") String postId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletedPost(postId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable("postId") String postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPostById(postId));
    }

    @GetMapping("/post/user/{userId}")
    public ResponseEntity<List<PostResponse>> findPostByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAllPostByUserId(userId));
    }

    //USer luu bai post
//    @PutMapping("/post/save/{postId}")
//    public ResponseEntity<String> savedPost(@PathVariable("postId")String postId) throws Exception {
//        return ResponseEntity.status(HttpStatus.OK).body(postService.savedPost(postId));
//    }

    @PutMapping("/post/like/{postId}")
    public ResponseEntity<PostResponse> likedPost(@PathVariable("postId")String postId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(postService.likedPost(postId));
    }

}
