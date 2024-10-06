package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.CommentRequest;
import com.example.DACN.Dto.Request.PostRequest;
import com.example.DACN.Dto.Response.CommentResponse;
import com.example.DACN.Dto.Response.PostResponse;
import com.example.DACN.Entity.Comment;
import com.example.DACN.Entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "liked", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toComment(CommentRequest commentRequest);

    Comment toComment(CommentResponse commentResponse);


    CommentResponse toCommentResponse(Comment comment);





}
