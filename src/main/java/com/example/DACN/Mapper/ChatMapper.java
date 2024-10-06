package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.ChatRequest;
import com.example.DACN.Dto.Request.CommentRequest;
import com.example.DACN.Dto.Response.ChatResponse;
import com.example.DACN.Dto.Response.CommentResponse;
import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "chatImage", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "groupLead", ignore = true)
    Chat toChat(ChatRequest chatRequest);

    Chat toChat(ChatResponse chatResponse);


    ChatResponse toChatResponse(Chat chat);





}
