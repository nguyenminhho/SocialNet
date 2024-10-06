package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.PostRequest;
import com.example.DACN.Dto.Request.StoryRequest;
import com.example.DACN.Dto.Response.PostResponse;
import com.example.DACN.Dto.Response.StoryResponse;
import com.example.DACN.Entity.Post;
import com.example.DACN.Entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoryMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "liked", ignore = true)
    @Mapping(target = "user", ignore = true)
    Story toStory(StoryRequest storyRequest);

    Story toStory(StoryResponse storyResponse);


    StoryResponse toStoryResponse(Story story);





}
