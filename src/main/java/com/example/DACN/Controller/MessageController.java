package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.ChatRequest;
import com.example.DACN.Dto.Request.MessageRequest;
import com.example.DACN.Dto.Response.ChatResponse;
import com.example.DACN.Dto.Response.MessageResponse;
import com.example.DACN.Service.ChatService;
import com.example.DACN.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    MessageService messageService;


    @PostMapping("/message/add/chat/{chatId}")
    public ResponseEntity<MessageResponse> addChat(@ModelAttribute MessageRequest messageRequest,
                                                   @RequestParam("files") List<MultipartFile> files,
                                                   @PathVariable String chatId) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(messageService.addMessageInChat(messageRequest, files,chatId));
    }

    @GetMapping("/message/{messageId}")
    public ResponseEntity<MessageResponse> getChat(@PathVariable String  messageId){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findMessageById(messageId));
    }

    @DeleteMapping("/message/delete/{messageId}")
    public ResponseEntity<Boolean> deleteStory(@PathVariable String  messageId){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.deleteMessage(messageId));
    }

    @GetMapping("/MessageByUser/{userId}/chat/{chatId}")
    public ResponseEntity<List<MessageResponse>> getAllChat(@PathVariable String userId,@PathVariable String chatId){

        return ResponseEntity.status(HttpStatus.OK).body(messageService.findMessageByUserId(userId,chatId));

    }



}
