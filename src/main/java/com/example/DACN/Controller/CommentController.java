package com.example.DACN.Controller;


import com.example.DACN.Dto.Request.CommentRequest;
import com.example.DACN.Dto.Response.CommentResponse;
import com.example.DACN.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/comment/post/{postId}/add")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest commentRequest, @PathVariable String postId){

        return ResponseEntity.status(HttpStatus.OK).body(commentService.addComment(commentRequest,postId));
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable String  commentId){

        return ResponseEntity.status(HttpStatus.OK).body(commentService.findCommentById(commentId));
    }

    @GetMapping("/allCommentByUser/{userId}")
    public ResponseEntity<List<CommentResponse>> getAllComment(@PathVariable String userId){


        return ResponseEntity.status(HttpStatus.OK).body(commentService.findCommentByUserId(userId));

    }

    @PutMapping("/comment/like/{commentId}")
    public ResponseEntity<CommentResponse> likedComment(@PathVariable String commentId){

        return ResponseEntity.status(HttpStatus.OK).body(commentService.likedComment(commentId));
    }

}
