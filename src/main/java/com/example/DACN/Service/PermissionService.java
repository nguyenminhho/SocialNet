package com.example.DACN.Service;

import com.example.DACN.Dto.Request.PermissionRequest;

import com.example.DACN.Dto.Response.PermissionResponse;

import com.example.DACN.Entity.Permission;

import com.example.DACN.Mapper.PermissionMapper;

import com.example.DACN.Repository.PermissionRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;
    public PermissionResponse addPermission(PermissionRequest permissionRequest) throws Exception{
        Permission permission = permissionMapper.toPermission(permissionRequest);
        if((permissionRepository.findById(permission.getName())).isPresent()){
            throw  new Exception("Permission exits");
        }
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getAllPermission(){
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public PermissionResponse getPermission(String name){
        return permissionMapper.toPermissionResponse(permissionRepository.findById(name).get());
    }

    public Boolean removePermission(String name){
        permissionRepository.deleteById(name);
        return true;
    }


}
