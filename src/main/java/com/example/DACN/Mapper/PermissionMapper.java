package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.PermissionRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Response.PermissionResponse;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Entity.Permission;
import com.example.DACN.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);





}
