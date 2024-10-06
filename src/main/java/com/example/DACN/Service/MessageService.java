package com.example.DACN.Service;


import com.example.DACN.Dto.Request.ChatRequest;
import com.example.DACN.Dto.Request.MessageRequest;
import com.example.DACN.Dto.Response.ChatResponse;
import com.example.DACN.Dto.Response.MessageResponse;
import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Message;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.ChatMapper;
import com.example.DACN.Mapper.MessageMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.ChatRepository;
import com.example.DACN.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ChatMapper chatMapper;
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    CloudinaryService cloudinaryService;


    public MessageResponse addMessageInChat(MessageRequest messageRequest, List<MultipartFile> files, String chatId) throws IOException {
        Message savedMessage = messageMapper.toMessage(messageRequest);
        User user = userMapper.toUser(userService.getMyInfo());
        Chat chat = chatMapper.toChat(chatService.findChatById(chatId));

        savedMessage.setImage(cloudinaryService.uploadImages(files));
        savedMessage.setCreatedAt(LocalDateTime.now());
        savedMessage.setChat(chat);
        savedMessage.setUser(user);

        return messageMapper.toMessageResponse(messageRepository.save(savedMessage));
    }


    public MessageResponse findMessageById(String messageId) {
        return messageMapper.toMessageResponse(messageRepository.findById(messageId).get());
    }



    public List<MessageResponse> findMessageByUserId(String userId, String chatId) {

        return messageRepository.findAllChatByUserId(userId,chatId).stream().map(m -> messageMapper.toMessageResponse(m)).toList();
    }

    public boolean deleteMessage(String messageId) {
        Message message = messageRepository.findById(messageId).get();
        messageRepository.delete(message);
        return true;
    }



}
