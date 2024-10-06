package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.PostRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Response.PostResponse;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "liked", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comment", ignore = true)
    Post toPost(PostRequest postRequest);

    Post toPost(PostResponse postResponse);


    PostResponse toPostResponse(Post post);





}
