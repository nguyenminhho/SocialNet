package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "savedPost", ignore = true)
    User toUser(UserRequest userRequest);


    UserResponse toUserResponse(User user);
    User toUser(UserResponse userResponse);





}
