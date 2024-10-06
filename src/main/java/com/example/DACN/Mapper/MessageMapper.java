package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.ChatRequest;
import com.example.DACN.Dto.Request.MessageRequest;
import com.example.DACN.Dto.Response.ChatResponse;
import com.example.DACN.Dto.Response.MessageResponse;
import com.example.DACN.Entity.Chat;
import com.example.DACN.Entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "chat", ignore = true)
    Message toMessage(MessageRequest messageRequest);

    Message toMessage(MessageResponse messageResponse);


    MessageResponse toMessageResponse(Message message);





}
