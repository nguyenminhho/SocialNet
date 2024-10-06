package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.PermissionRequest;
import com.example.DACN.Dto.Request.RoleRequest;
import com.example.DACN.Dto.Response.PermissionResponse;
import com.example.DACN.Dto.Response.RoleResponse;
import com.example.DACN.Entity.Permission;
import com.example.DACN.Entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Roles toRoles(RoleRequest roleRequest);


    RoleResponse toRoleResponse(Roles roles);





}
