package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.ChatRequest;
import com.example.DACN.Dto.Request.StoryRequest;
import com.example.DACN.Dto.Response.ChatResponse;
import com.example.DACN.Dto.Response.StoryResponse;
import com.example.DACN.Service.ChatService;
import com.example.DACN.Service.StoryService;
import com.example.DACN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @Autowired
    ChatService chatService;


    @PostMapping("/chat/add/{reqUserId}")
    public ResponseEntity<ChatResponse> addChat(@ModelAttribute ChatRequest chatRequest,
                                                @RequestParam("file") MultipartFile file,
                                                @PathVariable String reqUserId) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(chatService.addChat(chatRequest, file,reqUserId));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<ChatResponse> getChat(@PathVariable String  chatId){
        return ResponseEntity.status(HttpStatus.OK).body(chatService.findChatById(chatId));
    }

    @PutMapping("/addUser/{userId}/chat/{chatId}")
    public ResponseEntity<ChatResponse> addUserForChat(@PathVariable String  chatId, @PathVariable String  userId){
        return ResponseEntity.status(HttpStatus.OK).body(chatService.addNewUserForChat(userId,chatId));
    }

    @DeleteMapping("/chat/delete/{chatId}")
    public ResponseEntity<Boolean> deleteStory(@PathVariable String  chatId){
        return ResponseEntity.status(HttpStatus.OK).body(chatService.deleteChat(chatId));
    }

//    @GetMapping("/ChatByUser/{userId}")
//    public ResponseEntity<List<ChatResponse>> getAllChat(@PathVariable String userId){
//
//        return ResponseEntity.status(HttpStatus.OK).body(chatService.findChatByUserId(userId));
//
//    }



}
