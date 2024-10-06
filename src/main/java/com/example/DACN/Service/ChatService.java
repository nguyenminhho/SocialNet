package com.example.DACN.Service;


import com.example.DACN.Dto.Request.ChatRequest;
import com.example.DACN.Dto.Request.StoryRequest;
import com.example.DACN.Dto.Response.ChatResponse;
import com.example.DACN.Dto.Response.StoryResponse;
import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Story;
import com.example.DACN.Entity.User;
import com.example.DACN.Mapper.ChatMapper;
import com.example.DACN.Mapper.StoryMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.ChatRepository;
import com.example.DACN.Repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    UserService userService;

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ChatMapper chatMapper;

    @Autowired
    CloudinaryService cloudinaryService;


    public ChatResponse addChat(ChatRequest chatRequest, MultipartFile file,String reqUserId) throws IOException {
        Chat savedChat = chatMapper.toChat(chatRequest);
        User user = userMapper.toUser(userService.getMyInfo());
        User reqUser = userMapper.toUser(userService.getUser(reqUserId));

        List<User> listChat = new ArrayList<>();
        listChat.add(user);
        listChat.add(reqUser);

        savedChat.setGroupLead(user.getUsername());
        savedChat.setCreatedAt(LocalDateTime.now());
        savedChat.setChatImage(cloudinaryService.uploadImage(file));
        savedChat.setUser(listChat);

        return chatMapper.toChatResponse(chatRepository.save(savedChat));
    }


    public ChatResponse findChatById(String chatId) {
        return chatMapper.toChatResponse(chatRepository.findById(chatId).get());
    }

    public ChatResponse addNewUserForChat(String userId, String chatId) {
        User newUser = userMapper.toUser(userService.getUser(userId));
        Chat chat = chatMapper.toChat(findChatById(chatId));

        chat.getUser().add(newUser);

        return chatMapper.toChatResponse(chatRepository.save(chat));
    }


//    public List<ChatResponse> findChatByUserId(String userId) {
//
//        return chatRepository.findAllChatByUserId(userId).stream().map(c -> chatMapper.toChatResponse(c)).toList();
//    }

    public boolean deleteChat(String chatId) {
        Chat chat = chatRepository.findById(chatId).get();
        chatRepository.delete(chat);
        return true;
    }



}
