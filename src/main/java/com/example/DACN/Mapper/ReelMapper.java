package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.ReelRequest;
import com.example.DACN.Dto.Request.StoryRequest;
import com.example.DACN.Dto.Response.ReelResponse;
import com.example.DACN.Dto.Response.StoryResponse;
import com.example.DACN.Entity.Reels;
import com.example.DACN.Entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReelMapper {
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "video", ignore = true)
    @Mapping(target = "liked", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reels toReels(ReelRequest reelRequest);

    Reels toReels(ReelResponse reelResponse);


    ReelResponse toReelResponse(Reels reels);





}
